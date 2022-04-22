package ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

//ava Program --arraySize=13 --threadsCount=3
public class Program {

    public static int maxArrayElements = 2000000;
    public static int maxModuloValue = 1000;

    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception("Number of arguments greater than 1");

        int arraySize = 0;
        if (args[0].startsWith("--arraySize="))
            arraySize = Integer.parseInt(args[0].substring(12));
        else if (!args[0].startsWith("--arraySize=") || arraySize > maxArrayElements)
            throw new Exception("Incorrect array size");

        int threadsCount = 0;
        if (args[1].startsWith("--threadsCount="))
            threadsCount = Integer.parseInt(args[1].substring(15));
        if (!args[1].startsWith("--threadsCount=") || threadsCount > arraySize)
            throw new Exception("Incorrect threads count");

        List<Integer> randomInts = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i++)
            randomInts.add((int)(Math.random() * 2 * maxModuloValue - maxModuloValue));

        int randomIntsSum = 0;
        for (Integer value : randomInts)
            randomIntsSum += value;
        System.out.println("Sum: " + randomIntsSum);

        int range = arraySize/threadsCount + 1;
//        System.out.println(range);
        List<Thread> threads = new ArrayList<>(threadsCount);
        int rangeBeginIndex = 0;
        int rangeEndIndex = 0;

//        for (Integer value : randomInts)
//            System.out.print(value + " ");
//        System.out.print("\n");
//        System.out.println("range: " + rangeBeginIndex + " / " +rangeEndIndex);
//        for (Integer value : randomInts.subList(rangeBeginIndex, rangeEndIndex))
//            System.out.print(value + " ");
//        System.out.print("\n");

        for (int i = 0; i < threadsCount; i++) {
            rangeBeginIndex = i * range;
            rangeEndIndex = (i + 1) * range;

            if (rangeEndIndex > arraySize)
                rangeEndIndex = arraySize;

//            System.out.println("range: " + rangeBeginIndex + " / " +rangeEndIndex);
//            for (Integer value : randomInts.subList(rangeBeginIndex, rangeEndIndex))
//                System.out.print(value + " ");
//            System.out.print("\n");

            List<Integer> arrayForThread = randomInts.subList(rangeBeginIndex, rangeEndIndex);
            Thread tmp = new ThreadsController(arrayForThread, rangeBeginIndex, rangeEndIndex);
            tmp.setName("Thread " + (1 + i));
            threads.add(tmp);
        }



        for (Thread thread : threads)
            thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum by threads: " + ThreadsController.getTotal());
    }
}

class ThreadsController extends Thread {

    private static int total = 0;
    private final int rangeBeginIndex;
    private final int rangeEndIndex;
    private int sumOfThread = 0;

    public ThreadsController(List<Integer> data, int rangeBeginIndex, int rangeEndIndex) {
        this.rangeBeginIndex = rangeBeginIndex;
        this.rangeEndIndex = rangeEndIndex;
        for (int value : data) {
            sumOfThread += value;
        }
    }

    private static synchronized void printSumOfThread(int sumOfThread, int rangeBeginIndex, int rangeEndIndex) {
        System.out.println(
                Thread.currentThread().getName() +
                        ": from " + rangeBeginIndex +
                        " to " + (rangeEndIndex - 1) +
                        " sum is " + sumOfThread);
        total += sumOfThread;
    }

    public static int getTotal() {
        return total;
    }

    @Override
    public void run() {
        printSumOfThread(sumOfThread, rangeBeginIndex, rangeEndIndex);
    }
}