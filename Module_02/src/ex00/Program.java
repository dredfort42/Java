package ex00;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {

    private static final String signaturesTxtPath = "/Users/dnovikov/Desktop/Java/Module_02/src/ex00/signatures.txt";
    private static final String processed = "PROCESSED";
    private static final String undefined = "UNDEFINED";
    private static final int maxSignatureLen = 42;
    private static final String terminator = "42";
    private static final String outputFileName = "/Users/dnovikov/Desktop/Java/Module_02/src/ex00/result.txt";

    public static void main(String[] args) {
        Map<String, String> signatures = new HashMap<>();

        try (FileInputStream signaturesStream = new FileInputStream(signaturesTxtPath)) {
            byte[] buffer = new byte[signaturesStream.available()];
            signaturesStream.read(buffer, 0, signaturesStream.available());

            String tmp = "";
            for (int i = 0; i < buffer.length; i++) {
                if ((char) buffer[i] != ' ')
                    tmp += (char) buffer[i];
            }

            String[] tmpKnownSignatures = tmp.split("\n");
            for (int i = 0; i < tmpKnownSignatures.length; i++) {
                String[] pair = tmpKnownSignatures[i].split(",");
                signatures.put(pair[1], pair[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);
        String userPath = in.nextLine();

        while (!userPath.equals(terminator)) {
            String fileSignature = "";

            try {
                FileOutputStream resultFile = new FileOutputStream(outputFileName, true);
                FileInputStream userFile = new FileInputStream(userPath);
                for (int i = 0; userFile.available() > 0 && i < maxSignatureLen; i++)
                    fileSignature += (String.format("%02X", userFile.read()));
                String value = "";
                for (String key : signatures.keySet()) {
                    if (fileSignature.startsWith(key)) {
                        value = signatures.get(key);
                        resultFile.write(value.getBytes());
                        resultFile.write('\n');
                        System.out.println(processed);
                    }
                }
                if (value == "")
                    System.out.println(undefined);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            userPath = in.nextLine();
        }
        in.close();
    }
}