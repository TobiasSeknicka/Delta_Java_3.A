package di;

import accounts.BankAccount;
import accounts.serialization.BankAccountGsonSerializationService;
import accounts.serialization.BankAccountJacksonXmlSerializationService;
import accounts.serialization.BankAccountSerializationService;
import accounts.serialization.BankAccountSerializeFactory;
import accounts.services.AccountService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import factories.*;
import factories.logging.AccountCreator;
import factories.logging.LoggingAccountCreator;
import factories.logging.TimedAccountCreator;
import generators.AccountNumberGenerator;
import notifiers.ConsoleNotifier;
import notifiers.Notifier;
import transfers.AccountTransferService;
import transfers.TransferLoggerService;
import transfers.TransferService;

// Tady (a jen tady) se vytvari vsechny sluzby a propojuji se pres konstruktory.
public class ContainerFactory {

    public Container create() {
        // spolecne zavislosti
        Notifier notifier = new ConsoleNotifier();
        TransactionFactory transactionFactory = new TransactionFactory();
        TransferLoggerService logger = new TransferLoggerService();

        // prevody
        TransferService transferService = new TransferService(logger, notifier, transactionFactory);
        AccountTransferService accountTransferService =
                new AccountTransferService(logger, transferService, notifier, transactionFactory);

        // ucty
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

        // ostatni factory
        OwnerFactory ownerFactory = new OwnerFactory();
        CreditCardFactory creditCardFactory = new CreditCardFactory();

        // serializace
        BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();
        BankAccountSerializationService gsonSerializationService =
                new BankAccountGsonSerializationService(bankAccountSerializeFactory, new Gson());
        BankAccountSerializationService jacksonXmlSerializationService =
                new BankAccountJacksonXmlSerializationService(bankAccountSerializeFactory, new XmlMapper());

        return new Container(
                logger,
                transferService,
                accountTransferService,
                accountService,
                ownerFactory,
                creditCardFactory,
                gsonSerializationService,
                jacksonXmlSerializationService
        );
    }

    // logovani obaluje mereni casu
    private <T extends BankAccount> AccountCreator<T> decorate(AccountCreator<T> creator) {
        return new LoggingAccountCreator<>(new TimedAccountCreator<>(creator));
    }
}
