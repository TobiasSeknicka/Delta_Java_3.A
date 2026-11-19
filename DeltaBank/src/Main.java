import accounts.*;
import accounts.serialization.*;
import creditCards.CreditCard;
import factories.*;
import people.Owner;
import transactions.Transaction;
import transfers.AccountTransferService;
import transfers.TransferLoggerService;
import transfers.TransferService;
import accounts.services.AccountService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import factories.logging.AccountCreator;
import factories.logging.LoggingAccountCreator;
import factories.logging.TimedAccountCreator;
import generators.AccountNumberGenerator;
import notifiers.ConsoleNotifier;
import notifiers.Notifier;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        //dependency injection
        Notifier notifier = new ConsoleNotifier();
        TransactionFactory transactionFactory = new TransactionFactory();
        TransferLoggerService logger = new TransferLoggerService();

        TransferService transferService = new TransferService(logger, notifier, transactionFactory);
        AccountTransferService accountTransferService =
                new AccountTransferService(logger, transferService, notifier, transactionFactory);

        AccountNumberGenerator accountNumberGenerator = new AccountNumberGenerator();
        CurrentAccountFactory currentAccountFactory = new CurrentAccountFactory(accountNumberGenerator);
        StudentAccountFactory studentAccountFactory = new StudentAccountFactory(accountNumberGenerator);
        SavingAccountFactory savingAccountFactory = new SavingAccountFactory(accountNumberGenerator);
        BusinessAccountFactory businessAccountFactory = new BusinessAccountFactory(accountNumberGenerator);

        AccountService accountService = new AccountService(
                decorate(currentAccountFactory::createAccount),
                decorate(studentAccountFactory::createAccount),
                decorate(savingAccountFactory::createAccount),
                decorate(businessAccountFactory::createAccount)
        );

        OwnerFactory ownerFactory = new OwnerFactory();
        CreditCardFactory creditCardFactory = new CreditCardFactory();

        BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();

        Owner owner = ownerFactory.createOwner("Tobias", "Seknicka");

        BankAccount bankAccount = accountService.createCurrentAccount(owner, 2000);
        BankAccount studentAccount = accountService.createStudentAccount(owner, 100);
        BankAccount savingAccount = accountService.createSavingAccount(owner, 100);
        BankAccount businessAccount = accountService.createBusinessAccount(owner, 1000);
        CreditCard creditCard = creditCardFactory.createCreditCard(owner, 500);

        List<BankAccount> accounts = accountService.getAccounts();

        for (BankAccount account : accounts) {
            if (account instanceof InterestPoint) {
                ((InterestPoint) account).calculateInterest();
            }
        }

        for (BankAccount account : accounts) {
            if (account instanceof StudentAccount) {
                StudentAccount overrideAccount = (StudentAccount) account;
                System.out.println("school: " + overrideAccount.getSchool());
            }
        }

        transferService.withdraw(businessAccount, 50);
        transferService.addToBalance(savingAccount, 1000);

        transferService.addToBalance(bankAccount, 500);
        transferService.withdraw(bankAccount, 500);

        transferService.withdraw(studentAccount, 5000);

        transferService.addToBalance(creditCard, 1000);
        transferService.withdraw(creditCard, 100);

        accountTransferService.transfer(bankAccount, savingAccount, 300);
        accountTransferService.transfer(businessAccount, bankAccount, 100);

        try {
            accountTransferService.transfer(bankAccount, bankAccount, 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            accountTransferService.transfer(bankAccount, savingAccount, -50);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            accountTransferService.transfer(bankAccount, savingAccount, 1000000);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("current balance: " + bankAccount.getBalance());
        System.out.println("saving balance: " + savingAccount.getBalance());
        System.out.println("business balance: " + businessAccount.getBalance());
        System.out.println("student balance: " + studentAccount.getBalance());
        System.out.println("credit card balance: " + creditCard.getBalance());

        System.out.println("current number: " + bankAccount.getAccountNumber());
        System.out.println("student number: " + studentAccount.getAccountNumber());
        System.out.println("saving number: " + savingAccount.getAccountNumber());
        System.out.println("business number: " + businessAccount.getAccountNumber());

        System.out.println("=== HISTORIE TRANSAKCI ===");
        logger.printAll();

        System.out.println("=== HISTORIE BEZNEHO UCTU ===");
        for (Transaction t : logger.getByAccount(bankAccount.getAccountNumber())) {
            System.out.println(t);
        }

        List<BankAccountSerializationService> serializers = new ArrayList<>();
        serializers.add(new BankAccountGsonSerializationService(bankAccountSerializeFactory, new Gson()));
        serializers.add(new BankAccountJacksonXmlSerializationService(bankAccountSerializeFactory, new XmlMapper()));

        for (BankAccountSerializationService serializer : serializers) {
            System.out.println("=== " + serializer.getClass().getSimpleName() + " ===");
            System.out.println(serializer.serialize(bankAccount));
            System.out.println(serializer.serializeAll(accounts));
        }

    }

    // logovani obaluje mereni casu
    private static <T extends BankAccount> AccountCreator<T> decorate(AccountCreator<T> creator) {
        return new LoggingAccountCreator<>(new TimedAccountCreator<>(creator));
    }
}
