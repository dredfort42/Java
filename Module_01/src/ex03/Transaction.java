package ex03;

import java.util.UUID;

public class Transaction {

    private UUID _identifier;

    private User _recipient;

    private User _sender;

    private enum Category {
        DEBIT,
        CREDIT
    }

    private Category _category;

    private Integer _amount;

    public Transaction(User sender, User recipient, Integer amount) {
        _recipient = recipient;
        _sender = sender;
        _identifier = UUID.randomUUID();

        if (amount < 0) {
            _category = Category.CREDIT;
        } else {
            _category = Category.DEBIT;
        }

        _amount = amount;
        if (sender.getBalance() < 0 || sender.getBalance() < amount)
            System.err.println("Transaction failed!");
        else {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
        }
    }

    public UUID getIdentifier() {
        return _identifier;
    }

    public void setRecipient(User recipient) {
        _recipient = recipient;
    }

    public User getRecipient() {
        return _recipient;
    }

    public void setSender(User sender) {
        _sender = sender;
    }

    public User getSender() {
        return _sender;
    }

    public void setCategory(Category category) {
        _category = category;
    }

    public Category getCategory() {
        return _category;
    }

    public void setAmount(Integer amount) {
        _amount = amount;
    }

    public Integer getAmount() {
        return _amount;
    }

}
