package transfers;

import accounts.BusinessAccount;
import accounts.CurrentAccount;
import accounts.StudentAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import people.Owner;

import static org.junit.jupiter.api.Assertions.*;
class TransferServiceTest {

    private static final double DELTA = 0.001;

    private TransferService transferService;
    private Owner owner;

    @BeforeEach
    public void setUp() {
        transferService = new TransferService(new TransferLoggerService());
        owner = new Owner("Jan", "Novak");
    }

    @Test
    public void withdrawReducesBalance() {
        CurrentAccount account = new CurrentAccount(owner, 1000);

        transferService.withdraw(account, 300);

        assertEquals(700, account.getBalance(), DELTA);
    }

    @Test
    public void withdrawWholeBalanceIsAllowed() {
        CurrentAccount account = new CurrentAccount(owner, 1000);

        transferService.withdraw(account, 1000);

        assertEquals(0, account.getBalance(), DELTA);
    }

    @Test
    public void withdrawOverBalanceThrowsAndKeepsBalance() {
        CurrentAccount account = new CurrentAccount(owner, 1000);

        assertThrows(RuntimeException.class, () -> transferService.withdraw(account, 1001));
        assertEquals(1000, account.getBalance(), DELTA);
    }

    @Test
    public void businessAccountPaysWithdrawFee() {
        BusinessAccount account = new BusinessAccount(owner, 1000);

        transferService.withdraw(account, 100);

        // 1000 - 100 - 1 % fee
        assertEquals(899, account.getBalance(), DELTA);
    }

    @Test
    public void businessAccountFeeCountsTowardsLimit() {
        BusinessAccount account = new BusinessAccount(owner, 1000);

        assertThrows(RuntimeException.class, () -> transferService.withdraw(account, 1000));
        assertEquals(1000, account.getBalance(), DELTA);
    }

    @Test
    public void studentAccountCanGoNegativeUpToLimit() {
        StudentAccount account = new StudentAccount(owner, 0);

        transferService.withdraw(account, 5000);

        assertEquals(-5000, account.getBalance(), DELTA);
    }

    @Test
    public void studentAccountOverLimitThrows() {
        StudentAccount account = new StudentAccount(owner, 0);

        assertThrows(RuntimeException.class, () -> transferService.withdraw(account, 5001));
        assertEquals(0, account.getBalance(), DELTA);
    }

    @Test
    public void addToBalanceIncreasesBalance() {
        CurrentAccount account = new CurrentAccount(owner, 1000);

        transferService.addToBalance(account, 500);

        assertEquals(1500, account.getBalance(), DELTA);
    }

}