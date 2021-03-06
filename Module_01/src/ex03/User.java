package ex03;

public class User {

    public static TransactionsLinkedList _transactions = new TransactionsLinkedList();

    private Integer _identifier;
    private String _name;

    private Integer _balance;

    public User(String name, Integer balance) {
        if (setName(name) && setBalance(balance)) {
//            _id++;
            _identifier = UserIdsGenerator.getInstance().generateId();
            _name = name;
            _balance = balance;
        } else {
            _name = "";
            _balance = 0;
        }
    }

    public void addTransaction(Transaction transaction) {
        _transactions.addTransaction(transaction);
    }

    public Transaction[] getTransactionsArray() {
        return _transactions.toArray();
    }

    public Integer getId() {
        return _identifier;
    }

    public boolean setName(String name) {
        if (name.length() > 0) {
            _name = name;
            return true;
        }
        System.err.println("Incorrect name!");
        return false;
    }

    public String getName() {
        return _name;
    }

    public boolean setBalance(Integer balance) {
        if (balance >= 0) {
            _balance = balance;
            return true;
        }
        System.err.println("Incorrect balance!");
        return false;
    }

    public Integer getBalance() {
        return _balance;
    }
}