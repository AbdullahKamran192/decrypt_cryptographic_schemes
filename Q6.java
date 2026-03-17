import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q6 {

    public static char vigenereEncrypt(char plainChar, char keyChar) {
        int p = plainChar - 'a';
        int k = keyChar - 'a';
        return (char) ((p + k) % 26 + 'a');
    }

    public static void main(String[] args) {
        StringBuilder corruptedKey = new StringBuilder("x?ah??nh?bv");
        String cipherText = "xhtlhjqltdz";

        List<String> words = new ArrayList<>();
        File myObj = new File("11-letter-words.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String word = myReader.nextLine().trim().toLowerCase();
                if (word.length() == 11) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
        }

        for (char question1 = 'a'; question1 <= 'z'; question1++) {
            corruptedKey.setCharAt(1, question1);

            for (char question2 = 'a'; question2 <= 'z'; question2++) {
                corruptedKey.setCharAt(4, question2);

                for (char question3 = 'a'; question3 <= 'z'; question3++) {
                    corruptedKey.setCharAt(5, question3);

                    for (char question4 = 'a'; question4 <= 'z'; question4++) {
                        corruptedKey.setCharAt(8, question4);

                        String fullKey = corruptedKey.toString();

                        for (String word11 : words) {
                            boolean matches = true;

                            for (int i = 0; i < 11; i++) {
                                char keyChar = fullKey.charAt(i);
                                char plainChar = word11.charAt(i);
                                char cipherChar = cipherText.charAt(i);

                                if (vigenereEncrypt(plainChar, keyChar) != cipherChar) {
                                    matches = false;
                                    break;
                                }
                            }

                            if (matches) {
                                System.out.println("Full key: " + fullKey);
                                System.out.println("Plaintext: " + word11);
                                return;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("No match found.");
    }
}