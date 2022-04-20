package ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Array;
import java.util.*;

public class Program {

    public static int maxFileSize = 10;
    public static List<String> txtA = new ArrayList<>();
    public static List<String> txtB = new ArrayList<>();
    public static Set<String> dictionary = new HashSet<>();

    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception("Number of arguments greater than 2");
        BufferedReader readerA = null;
        BufferedReader readerB = null;
        BufferedWriter Writer;
        try {
            readerA = new BufferedReader(new FileReader(args[0]));
            readerB = new BufferedReader(new FileReader(args[1]));

            String line;
            while ((line = readerA.readLine()) != null)
                txtA.addAll(Arrays.asList(line.split(" ")));
            while ((line = readerB.readLine()) != null)
                txtB.addAll(Arrays.asList(line.split(" ")));

            dictionary.addAll(txtA);
            dictionary.addAll(txtB);

            System.out.println(txtA);
            System.out.println(txtB);
            System.out.println(dictionary);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
