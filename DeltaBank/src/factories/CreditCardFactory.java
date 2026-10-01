package factories;

import creditCards.CreditCard;
import people.Owner;

public class CreditCardFactory {
    public CreditCard createCreditCard(Owner owner, double balance) {
        return new CreditCard(owner, balance);
    }
}
