package banking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts = new LinkedHashMap<>();

    public Bank() {
    }

    private Account getAccount(Long accountNumber) {
        Set<Map.Entry<Long, Account>> entries = accounts.entrySet();
        Optional<Map.Entry<Long, Account>> first = entries.stream()
                .filter(longAccountEntry -> longAccountEntry.getKey().toString().equals(accountNumber.toString()))
                .findFirst();
        if (first.isPresent()) {
            return first.get().getValue();
        } else {
            throw new NoSuchElementException("not found");
        }
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        if (company == null) {
            throw new IllegalStateException("company not found");
        }
        long nextLong = getNextLong();
        CommercialAccount commercialAccount = new CommercialAccount(company, nextLong, pin, startingDeposit);
        accounts.put(nextLong, commercialAccount);
        return commercialAccount.getAccountNumber();
    }

    private long getNextLong() {
        Random rand = new Random();
        return Math.abs(rand.nextLong());
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        if (person == null) {
            throw new IllegalStateException("company not found");
        }
        long nextLong = getNextLong();
        ConsumerAccount consumerAccount = new ConsumerAccount(person, nextLong, pin, startingDeposit);
        accounts.put(nextLong, consumerAccount);
        return consumerAccount.getAccountNumber();
    }

    public double getBalance(Long accountNumber) {
        if (accountNumber == null) {
            throw new IllegalStateException("accountNumber not set");
        }
        Account account = getAccount(accountNumber);
        return account.getBalance();
    }

    public void credit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        account.creditAccount(amount);
    }

    public boolean debit(Long accountNumber, double amount) {
        if (accountNumber == null) {
            throw new IllegalStateException("accountNumber not set");
        }
        Account account = getAccount(accountNumber);
        return account.debitAccount(amount);
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        if (accountNumber == null) {
            throw new IllegalStateException("firstName not set");
        }
        if (pin < 0) {
            throw new IllegalStateException("this value cannot be negative.");
        }
        Account account = getAccount(accountNumber);
        return account.validatePin(pin);
    }

    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = getAccount(accountNumber);
        AccountHolder accountHolder = account.getAccountHolder();
        CommercialAccount commercialAccount = new CommercialAccount((Company) accountHolder, accountNumber, 0, 0);
        commercialAccount.addAuthorizedUser(authorizedPerson);
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        if (accountNumber == null) {
            throw new IllegalStateException("accountNumber not found");
        }
        if (authorizedPerson == null) {
            throw new IllegalStateException("authorizedPerson not found");
        }
        Account account = getAccount(accountNumber);
        AccountHolder accountHolder = account.getAccountHolder();
        CommercialAccount commercialAccount = new CommercialAccount((Company) accountHolder, accountNumber, 0, 0);
        return commercialAccount.isAuthorizedUser(authorizedPerson);
    }

    public Map<String, Double> getAverageBalanceReport() {
        Collection<Account> values = accounts.values();

        if (values.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Map<String, Double> commercialAvg = values.stream().map(Account::getBalance).collect(Collectors.groupingBy(
                account -> CommercialAccount.class.toString(), Collectors.averagingDouble(Double::doubleValue)));

        Map<String, Double> consumerAvg = values.stream().map(Account::getBalance).collect(Collectors.groupingBy(
                account -> ConsumerAccount.class.toString(), Collectors.averagingDouble(Double::doubleValue)));

        HashMap<String, Double> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.putAll(commercialAvg);
        objectObjectHashMap.putAll(consumerAvg);

        return objectObjectHashMap;
    }
}
