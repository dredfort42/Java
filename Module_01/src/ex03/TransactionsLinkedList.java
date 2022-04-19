package ex03;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionsLinkedList	_next;
    private TransactionsLinkedList	_previous;
    private Transaction		_data;

    public TransactionNode(TransactionsLinkedList next, TransactionsLinkedList previous, Transaction data) {
        _next = next;
        _previous = previous;
        _data = data;
    }

    public TransactionNode getNext() {
        return next;
    }

    public void setNext(TransactionNode next) {
        this.next = next;
    }

    public TransactionNode getPrevious() {
        return previous;
    }

    public void setPrevious(TransactionNode previous) {
        this.previous = previous;
    }

    public Transaction getData() {
        return data;
    }

    public void setData(Transaction data) {
        this.data = data;
    }
}
