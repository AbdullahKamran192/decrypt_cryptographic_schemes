import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class Q2_reattempt {

    public static char encryptCaesarPlus(int[] tripleValues, char key, char messageChar) {
        if ('a' <= messageChar && messageChar <= 'z') {
            int p = messageChar - 'a';
            int k = key - 'a';

            int encryptedChar =
                    (tripleValues[0] * p * p * p
                    + tripleValues[1] * p * p
                    + tripleValues[2] * p
                    + k) % 26;

            return (char) (encryptedChar + 'a');
        }

        return messageChar;
    }

    public static void main(String[] args) {
        File myObj = new File("words_alpha.txt");
        String encryptedMessage = "Zvu qipuhegh okx, avux huqzexm ex qkduzs, lguq xgz dghmuz zvkz lkxmuh oks cgou. Avux ex k qzkzu gd qucihezs vu lguq xgz dghmuz zvu pgqqetefezs gd hiex. Avux kff eq ghluhfs, vu lguq xgz dghmuz zvkz leqghluh oks cgou. Zviq veq puhqgx eq xgz uxlkxmuhul, kxl veq Qzkzuq kxl kff zvueh cfkxq khu phuquhrul. - Cgxdiceiq";
        int entries = 0;
        HashMap<String, String> englishWords = new HashMap<String, String>();

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().toLowerCase();
                englishWords.put(data, data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
        }

        for (int a = 1; a < 26; a++) {
            for (int b = 1; b < 26; b++) {
                for (int c = 0; c < 26; c++) {
                    for (char key = 'a'; key <= 'z'; key++) {

                        boolean worksForWholeMessage = true;
                        StringBuilder pattern = new StringBuilder();
                        StringBuilder potentialPlaintext = new StringBuilder();

                        for (int i = 0; i < encryptedMessage.length(); i++) {
                            char cipherChar = encryptedMessage.charAt(i);

                            if (!Character.isLetter(cipherChar)) {
                                pattern.append(cipherChar);
                                potentialPlaintext.append(cipherChar);
                                continue;
                            }

                            char cipherLower = Character.toLowerCase(cipherChar);
                            StringBuilder matches = new StringBuilder();

                            for (char plainChar = 'a'; plainChar <= 'z'; plainChar++) {
                                if (encryptCaesarPlus(new int[]{a, b, c}, key, plainChar) == cipherLower) {
                                    matches.append(plainChar);
                                }
                            }

                            if (matches.length() == 0) {
                                worksForWholeMessage = false;
                                break;
                            }

                            pattern.append("[").append(matches).append("]");

                            if (matches.length() != 1) {
                                worksForWholeMessage = false;
                                break;
                            }

                            char decodedChar = matches.charAt(0);

                            // preserve original case
                            if (Character.isUpperCase(cipherChar)) {
                                decodedChar = Character.toUpperCase(decodedChar);
                            }

                            potentialPlaintext.append(decodedChar);
                        }

                        if (worksForWholeMessage) {
                            String plaintext = potentialPlaintext.toString();
                            String[] words = plaintext.split(" ");

                            boolean allWordsEnglish = true;
                            for (String word : words) {
                                String cleanedWord = word.toLowerCase().replaceAll("[^a-z]", "");

                                if (!cleanedWord.isEmpty() && englishWords.get(cleanedWord) == null) {
                                    allWordsEnglish = false;
                                    break;
                                }
                            }

                            if (allWordsEnglish) {
                                entries++;
                                System.out.println(
                                    "a=" + a +
                                    ", b=" + b +
                                    ", c=" + c +
                                    ", key=" + key +
                                    " -> " + pattern +
                                    " -> " + plaintext
                                );
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Total valid entries: " + entries);
    }
}