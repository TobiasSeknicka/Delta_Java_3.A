package factories;

import people.Owner;

public class OwnerFactory {
    public Owner createOwner(String firstname, String lastname) {
        return new Owner(firstname, lastname);
    }
}
