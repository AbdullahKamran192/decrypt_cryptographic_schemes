import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q7 {

    public static String cipherText1 = "zqcucickhv";
    public static String cipherText2 = "cccygopdop";

    public static String encryptVigenere(String plainText, String key) {
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int p = plainText.charAt(i) - 'a';
            int k = key.charAt(i) - 'a';
            char encryptedChar = (char) (((p + k) % 26) + 'a');
            cipher.append(encryptedChar);
        }

        return cipher.toString();
    }

    public static String deriveKey(String plainText, String cipherText) {
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int p = plainText.charAt(i) - 'a';
            int c = cipherText.charAt(i) - 'a';
            char keyChar = (char) (((c - p + 26) % 26) + 'a');
            key.append(keyChar);
        }

        return key.toString();
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        File myObj = new File("10-Letter-Words.txt"); // change filename if needed

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String word = myReader.nextLine().trim().toLowerCase();

                if (word.length() == cipherText1.length()) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
        }

        for (String plainWord1 : words) {
            String key = deriveKey(plainWord1, cipherText1);

            for (String plainWord2 : words) {
                if (encryptVigenere(plainWord2, key).equals(cipherText2)) {
                    System.out.println("Key: " + key);
                    System.out.println("Plain Word 1: " + plainWord1);
                    System.out.println("Plain Word 2: " + plainWord2);
                    return;
                }
            }
        }

        System.out.println("No match found.");
    }
}