package banking;

/**
 * A bank transaction implementation.
 */
public class Transaction implements TransactionInterface {
    private Long accountNumber;
    private BankInterface bank;

    /**
     * @param bank          The bank where the account is housed.
     * @param accountNumber The customer's account number.
     * @param attemptedPin  The PIN entered by the customer.
     * @throws Exception Account validation failed.
     */
    public Transaction(BankInterface bank, Long accountNumber, int attemptedPin) throws Exception {
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        if (bank == null) {
            throw new IllegalStateException("bank not set ");
        }
        return this.bank.getBalance(accountNumber);
    }

    public void credit(double amount) {
        this.bank.credit(accountNumber, amount);
    }

    public boolean debit(double amount) {
        if (accountNumber == null) {
            throw new IllegalStateException("accountNumber not set");
        }
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalStateException("The value is not a valid number.");
        }
        return bank.debit(accountNumber, amount);
    }
}
