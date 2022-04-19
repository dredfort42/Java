import java.util.Scanner;

public class Program {
    private static final int maximumCodes = 65535;
    private static final int maxGraphHeight = 10;
    private static final int topSize = 10;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        input.close();
        short[] charCounter = countCharOccurrences(line);
        char[] top = getTop(charCounter);
        print(top, charCounter);
    }

    private static short[] countCharOccurrences(String line) {
        short[] tmp = new short[maximumCodes];
        for (int i = 0; i < line.length(); i++)
            tmp[line.toCharArray()[i]]++;
        return tmp;
    }

    private static char[] getTop(short[] charCounter) {
        char[] tmp = new char[maximumCodes];

        for (int i = 0; i < maximumCodes; i++) {
            short iCharCount = charCounter[i];
            if (iCharCount > 0) {
                for (int j = 0; j < maximumCodes; j++) {
                    if (charCounter[tmp[j]] < iCharCount) {
                        tmp = insertCharAt(tmp, (char)i, j);
                        break;
                    }
                }
            }
        }
        return (tmp);
    }

    private static char[] insertCharAt(char[] base, char c, int index) {
        char[] tmp = new char[maximumCodes];
        for (int i = 0; i < index; i++) {
            tmp[i] = base[i];
        }
        tmp[index] = c;
        for (int i = index + 1; i < maximumCodes; i++) {
            tmp[i] = base[i - 1];
        }
        return tmp;
    }

    private static void print(char[] top, short[] charCount) {
        short max = charCount[top[0]];
        final int maxHeight = (max <= maxGraphHeight ? max : maxGraphHeight);
        final int totalLines = maxHeight + 2;
        int[] graphs = new int[maximumCodes];

        for (int i = 0; i < maximumCodes; i++) {
            if (max <= 10)
                graphs[i] = charCount[top[i]];
            else
                graphs[i] = charCount[top[i]] * maxGraphHeight / max;
        }
        System.out.println();
        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < topSize; j++) {
                if (top[j] != 0) {
                    if (i + graphs[j] + 2 == totalLines) {
                        System.out.printf("%3d", charCount[top[j]]);
                    } else if (i == totalLines - 1) {
                        System.out.printf("%3c", top[j]);
                    } else if (i + graphs[j] >= maxHeight) {
                        System.out.printf("%3c", '#');
                    }
                    if (j != maximumCodes - 1 && top[j + 1] != 0 && i + graphs[j + 1] >= maxHeight) {
                        System.out.printf("%c", ' ');
                    }
                }
            }
            System.out.println();
        }
    }
}