public class Program {
    public static void main(String[] args) {
        int sixDigitNumber = 231561;
        int result = 0;

        result += sixDigitNumber % 10;
        sixDigitNumber /= 10;
        result += sixDigitNumber % 10;
        sixDigitNumber /= 10;
        result += sixDigitNumber % 10;
        sixDigitNumber /= 10;
        result += sixDigitNumber % 10;
        sixDigitNumber /= 10;
        result += sixDigitNumber % 10;
        sixDigitNumber /= 10;
        result += sixDigitNumber % 10;

        System.out.println(result);
    }
}