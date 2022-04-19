import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int     inputNumber = 11;
        boolean isPrime = true;

        Scanner input = new Scanner(System.in);
        inputNumber = input.nextInt();
        input.close();
        if (inputNumber < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        int i = 2;
        for (; i * i <= inputNumber; i++) {
            if (inputNumber % i == 0) {
                isPrime = false;
                break;
            }
        }

        System.out.println(isPrime + " " + (i - 1));
    }
}
