package banking;

/**
 * Abstract bank account.
 */
public abstract class Account implements AccountInterface {
    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pin;
    private double balance;

    protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = startingDeposit;
    }

    public AccountHolder getAccountHolder() {
        if (this.accountHolder == null) {
            throw new IllegalStateException("accountHolder not set");
        }
        return this.accountHolder;
    }

    public boolean validatePin(int attemptedPin) {
        if (attemptedPin < 0) {
            throw new IllegalStateException("this value cannot be negative.");
        }
        if (pin == attemptedPin) {
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        if (Double.isNaN(this.balance) || Double.isInfinite(this.balance)) {
            throw new IllegalStateException("The value is not a valid number.");
        }
        return this.balance;
    }

    public Long getAccountNumber() {
        if (this.accountNumber == null) {
            throw new IllegalStateException("accountNumber not set");
        }
        return this.accountNumber;
    }

    public void creditAccount(double amount) {
        balance = balance + amount;
    }

    public boolean debitAccount(double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalStateException("The value is not a valid number.");
        }
        if (balance >= amount) {
            this.balance = balance - amount;
            return true;
        } else {
            return false;
        }
    }
}
