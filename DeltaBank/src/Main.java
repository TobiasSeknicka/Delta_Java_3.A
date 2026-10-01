import accounts.*;
import creditCards.CreditCard;
import people.Owner;
import transfers.AccountTransferService;
import transfers.TransferService;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        TransferService transferService = new TransferService();
        AccountTransferService accountTransferService = new AccountTransferService();

        Owner owner = new Owner("Tobias", "Seknicka");

        List<BankAccount> accounts = new ArrayList<>();

        BankAccount bankAccount = new CurrentAccount(owner, 2000);
        accounts.add(bankAccount);

        BankAccount studentAccount = new StudentAccount(owner, 100);
        accounts.add(studentAccount);

        BankAccount savingAccount = new SavingAccount(owner, 100);
        accounts.add(savingAccount);

        BankAccount businessAccount = new BusinessAccount(owner, 1000);
        accounts.add(businessAccount);

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

        CreditCard creditCard = new CreditCard(owner, 500);
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
    }
}
