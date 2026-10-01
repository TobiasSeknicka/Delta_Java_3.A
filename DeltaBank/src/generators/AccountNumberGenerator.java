package generators;


import java.util.Random;

public class AccountNumberGenerator {

    Random random = new Random();
    public String generateAccountNumber() {
        String accountNumber = "";
        for (int i = 0; i < 10; i++) {
            accountNumber += random.nextInt(10);
        }
        return accountNumber;
    }
}
