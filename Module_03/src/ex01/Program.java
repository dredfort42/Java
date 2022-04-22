package ex01;

public class Program {

    public static boolean switchThread = false;

    public static synchronized void printHan() {
        if (!switchThread) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
        switchThread = !switchThread;
        Program.class.notify();
    }

    public static synchronized void printEgg() {
        if (switchThread) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Egg");
        switchThread = !switchThread;
        Program.class.notify();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1)
            throw new Exception("Number of arguments greater than 1");
        int count;
        if (args[0].startsWith("--count="))
            count = Integer.parseInt(args[0].substring(8));
        else
            count = Integer.parseInt(args[0]);
        if (count < 1)
            throw new Exception("Invalid argument: " + count);
        Egg egg = new Egg(count);
        Hen hen = new Hen(count);

        egg.start();
        hen.start();
    }
}

