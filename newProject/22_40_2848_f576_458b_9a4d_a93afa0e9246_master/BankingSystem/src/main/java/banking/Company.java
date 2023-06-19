package banking;

public class Company extends AccountHolder {
    private String companyName;

    public Company(String companyName, int taxId) {
        super(taxId);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        if (this.companyName == null) {
            throw new IllegalStateException("companyName not set");
        }
        return this.companyName;
    }
}
