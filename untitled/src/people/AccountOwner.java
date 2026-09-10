package people;

public class AccountOwner {

    private String uuid;

    private String name;

    private String lastName;

    // address, date of born, consents, ...


    public AccountOwner(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


