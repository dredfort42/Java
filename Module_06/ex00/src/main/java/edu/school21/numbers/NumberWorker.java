package edu.school21.numbers;

public class NumberWorker{

    public boolean isPrime(int number) {
        boolean isPrime = true;

        if (number < 2)
            throw new IllegalNumberException();
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public int digitsSum(int number) {
        int result = 0;
        while (number != 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    public class IllegalNumberException extends RuntimeException {
        public String toString() {
            return (">>> Hey! Hey! Hey! Illegal number Exception!");
        }
    }

}
