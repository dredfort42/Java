import java.util.Scanner;

public class Program {
    private static final int maxWeeksCount = 18;
    private static final int maxGradesCount = 5;
    private static final int minGrade = 1;
    private static final int maxGrade = 9;

    public static void main(String[] args) {
        int week = 0;
        long analytics = 0;

        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        while (week < maxWeeksCount && !line.equals("42")) {
            week++;
            if (!line.equals("Week " + week))
                error();
            analytics *= 10;
            analytics += getMin(input);
            line = input.nextLine();

        }
        input.close();
        for (int i = 0; i < week; i++) {
            System.out.print("Week " + (i + 1) + " ");
            drawGraph(getGrade(i ,week ,analytics));
        }
    }

    private static int getMin(Scanner input) {
        int min = 9;
        int tmp;

        for (int i = 0; i < maxGradesCount; i++) {
            tmp = input.nextInt();
            if (tmp > maxGrade || tmp < minGrade)
                error();
            min = (min < tmp) ? min : tmp;
        }
        input.nextLine();
        return min;
    }

    private static int getGrade(int i, int week, long analytics)	{
        int ret;

        for (; i < week - 1; i++) {
            analytics /= 10;
        }
        ret = (int)(analytics % 10);
        return (ret);
    }

    private static void drawGraph(int minGrade) {

        for (int i = 0; i < minGrade; i++) {
            System.out.print("=");
        }
        System.out.println(">");
    }

    private static void error() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }
}
