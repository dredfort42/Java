package ex00;

public class Program {
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
//        System.out.println(count);

        Egg egg = new Egg();
        Hen hen = new Hen();

        egg.startThread(count);
        hen.startThread(count);
        egg.start();
        hen.start();

        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < count; i++)
            System.out.println("Human");
    }
}
