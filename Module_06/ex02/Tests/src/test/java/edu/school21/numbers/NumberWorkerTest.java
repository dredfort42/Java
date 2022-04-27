package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    NumberWorker numberWorker;
    public
    @BeforeEach
    void numberWorkerInitialization() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource (ints = {-42, -21, -1, 0, 1})
    void isPrimeIncorrectArgs(int arg) {
        Assertions.assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(arg));
    }

    @ParameterizedTest
    @ValueSource (ints = {2, 3, 5, 7, 11, 23, 31, 43, 101, 499})
    void isPrimeTrue(int arg) {
        Assertions.assertTrue(numberWorker.isPrime(arg));
    }

    @ParameterizedTest
    @ValueSource (ints = {4, 6, 8, 10, 12, 24, 32, 46, 102, 498})
    void isPrimeFalse(int arg) {
        Assertions.assertFalse(numberWorker.isPrime(arg));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void digitsSumCheck(int number, int sum) {
        Assertions.assertEquals(numberWorker.digitsSum(number), sum);
    }

    @Test
    void toStringException() {
        Assertions.assertSame(new IllegalNumberException().toString(), "[ERROR] wrong argument");
    }
}
