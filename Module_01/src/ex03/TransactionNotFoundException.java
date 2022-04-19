package ex03;

public class TransactionNotFoundException extends RuntimeException {

    public String toString() {
        return ("/// Hey! Hey! Hey! Transaction not found!");
    }

}
