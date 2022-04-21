package ex00;

public class Hen extends Thread {
    private int count;

    public void startThread(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++)
            System.out.println("Hen");
    }
}