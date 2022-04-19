package ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeById(UUID uuid);
    Transaction[] toArray();
}