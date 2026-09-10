import accounts.*;
import people.AccountOwner;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        AccountOwner owner = new AccountOwner("Tobias", "Seknicka");

        List<BankAccount> accounts = new ArrayList<>();

        BankAccount bankAccount = new CurrentAccount(owner, 2000);
        accounts.add(bankAccount);

        BankAccount studentAccount = new StudentAccount(owner, 100);
        accounts.add(studentAccount);

        BankAccount savingAccount = new SavingAccount(owner, 100);

        BankAccount businessAccount = new BusinessAccount(owner, 100);

        for (BankAccount account : accounts) {

            if (account instanceof StudentAccount) {

                StudentAccount overrideAccount = (StudentAccount) account;
                System.out.println("school: " + overrideAccount.getSchool());
            }
        }

        businessAccount.sub(50);

        savingAccount.add(1000);

        bankAccount.add(500);
        bankAccount.add(300);
        bankAccount.add(100);

        bankAccount.sub(500);
        bankAccount.sub(500);
        bankAccount.sub(500);

        studentAccount.sub(5500);
        studentAccount.sub(200);




        System.out.println("balance: " + bankAccount.getBalance());
    }
}
