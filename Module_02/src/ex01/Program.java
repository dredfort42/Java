package ex01;

import java.io.*;
import java.util.*;

public class Program {

    public static int maxFileSizeMb = 10;
    public static String dictionaryFileName = "dictionary.txt";
    public static String dictionaryPath = "/Users/dnovikov/Desktop/Java/Module_02/src/ex01/" + dictionaryFileName;
    public static List<String> txtA = new ArrayList<>();
    public static List<String> txtB = new ArrayList<>();
    public static Set<String> dictionary = new HashSet<>();

    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception("Number of arguments greater than 2");
        try {
            File fileA = new File(args[0]);
            File fileB = new File(args[1]);
            if (fileA.length() / (1024 * 1024) > maxFileSizeMb || fileB.length() / (1024 * 1024) > maxFileSizeMb)
                throw new Exception("File too large!");
            BufferedReader readerA = new BufferedReader(new FileReader(args[0]));
            BufferedReader readerB = new BufferedReader(new FileReader(args[1]));
            BufferedWriter writer;
            String line;
            while ((line = readerA.readLine()) != null)
                txtA.addAll(Arrays.asList(line.split(" ")));
            while ((line = readerB.readLine()) != null)
                txtB.addAll(Arrays.asList(line.split(" ")));

            dictionary.addAll(txtA);
            dictionary.addAll(txtB);
//            System.out.println(txtA);
//            System.out.println(txtB);
//            System.out.println(dictionary);

            FileWriter toFile = new FileWriter(dictionaryPath);
            writer = new BufferedWriter(toFile);
            for (String w : dictionary) {
                writer.write(w + " ");
            }
            readerA.close();
            readerB.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Integer> frequencyOccurrenceA = getFrequency(txtA);
        List<Integer> frequencyOccurrenceB = getFrequency(txtB);
        int numerator = getNumerator(frequencyOccurrenceA, frequencyOccurrenceB);
//        System.out.println(numerator);
        double denominator = getDenominator(frequencyOccurrenceA, frequencyOccurrenceB);
//        System.out.println(denominator);
        double similarity = (double)((int)((numerator / denominator) * 100)) / 100;
        System.out.println("Similarity = " + similarity);
    }

    public static List<Integer> getFrequency(List<String> txt) {
        List<Integer> tmp = new ArrayList<>(dictionary.size());
        int i = 0;
        int counter = 0;
        for (String element : dictionary) {
            for (String fromFile : txt) {
                if (element.equals(fromFile))
                    counter++;
            }
            tmp.add(i, counter);
            i++;
            counter = 0;
        }
        return tmp;
    }

    public static int getNumerator(List<Integer> a, List<Integer> b) {
        int tmp = 0;
        for (int i = 0; i < dictionary.size(); i++) {
            tmp += a.get(i) * b.get(i);
//            System.out.println(a.get(i) + " :: " + b.get(i));
        }
        return tmp;
    }

    public static double getDenominator(List<Integer> a, List<Integer> b) {
        double pow2A = 0;
        for (Integer x : a)
            pow2A += x * x;
        double pow2B = 0;
        for (Integer x : b)
            pow2B += x * x;
        return Math.sqrt(pow2A) * Math.sqrt(pow2B);
    }
}
