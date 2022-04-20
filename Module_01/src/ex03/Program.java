package ex03;

public class Program {
    public static void main(String[] args) {
        User dima = new User("Dima", 1000);
        User kira = new User("Kira", 1000);
        dima.addTransaction(new Transaction(dima, kira, 100));
        dima.addTransaction(new Transaction(dima, kira, -200));
        dima.addTransaction(new Transaction(dima, kira, 300));
        dima.addTransaction(new Transaction(dima, kira, -400));
        printTransactionList(dima);

    }

//    public static void printUser(User user) {
//        System.out.print(user.getId() + "| name: " + user.getName());
//        System.out.println(" balance: " + user.getBalance());
//    }

    public static void printTransaction(Transaction t) {
        System.out.println(t.getSender().getName() + " -> "  + t.getRecipient().getName() + ", " + t.getAmount() + ", " + t.getCategory() + ", " + t.getIdentifier());
    }

    public static void printTransactionList(User user) {
        Transaction[] arr = user.getTransactionsArray();
        for (int i = arr.length - 1; i >= 0; i--)
            printTransaction(arr[i]);

    }
}
