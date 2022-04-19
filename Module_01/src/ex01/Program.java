package ex01;

public class Program {
    public static void main(String[] args) {
        User dima = new User("Dima", 100);
        printUser(dima);
        User kira = new User("Kira", 1000);
        printUser(kira);
        User test1 = new User("", 100);
        printUser(test1);
        User test2 = new User("Test2", -100);
        printUser(test2);
        User test3 = new User("", -1);
        printUser(test3);
        User alex = new User("Alex", 500);
        printUser(alex);
        printUser(dima);

//        Transaction t0 = new Transaction(dima, kira, 100);
//        printTransaction(t0);
//        printUser(dima);
//        printUser(kira);
//        Transaction t1 = new Transaction(dima, kira, -100);
//        printTransaction(t1);
////        Transaction t2 = new Transaction(dima, kira, 200);
////        printTransaction(t2);
//        printUser(dima);
    }

    public static void printUser(User user) {
        System.out.print(user.getId() + "| name: " + user.getName());
        System.out.println(" balance: " + user.getBalance());
    }

//    public static void printTransaction(Transaction t) {
//        System.out.println(t.getSender().getName() + " -> "  + t.getRecipient().getName() + ", " + t.getAmount() + ", " + t.getCategory() + ", " + t.getIdentifier());
//    }
}
