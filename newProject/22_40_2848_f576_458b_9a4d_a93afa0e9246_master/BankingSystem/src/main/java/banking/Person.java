package banking;

/**
 * The concrete Account holder of Person type.
 */
public class Person extends AccountHolder {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, int idNumber) {
        super(idNumber);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        if (this.firstName == null) {
            throw new IllegalStateException("firstName not set");
        }
        return this.firstName;
    }

    public String getLastName() {
        if (this.lastName == null) {
            throw new IllegalStateException("lastName not set");
        }
        return this.lastName;
    }
}
