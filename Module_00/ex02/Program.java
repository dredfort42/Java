import java.util.Scanner;

public class Program {
    public static final int terminator = 42;

    public static void main(String[] args) {
        int     counter = 0;
        int     inputNumber;
        Scanner input;

        input = new Scanner(System.in);
        inputNumber = input.nextInt();
        if (inputNumber < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        while (inputNumber != terminator) {
            if (isPrime(summarize(inputNumber)))
                counter++;
            inputNumber = input.nextInt();
        }
        input.close();
        System.out.println("Count of coffee-request - " + counter);
    }

    public static boolean isPrime(int summ) {
        for (int i = 2; i * i <= summ + 1; i++) {
            if (summ % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int summarize(int inputNumber) {
        int result = 0;

        while (inputNumber > 0) {
            result += inputNumber % 10;
            inputNumber /= 10;
        }
        return result;
    }
}

