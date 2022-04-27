package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number < 2)
            throw new IllegalNumberException();
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0)
               return false;
        }
        return true;
    }

    public int digitsSum(int number) {
        int result = 0;

        if (number < 0)
            number = -number;
        while (number != 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}

class IllegalNumberException extends RuntimeException {
    @Override
    public String toString() {
        return ("[ERROR] wrong argument");
    }
}