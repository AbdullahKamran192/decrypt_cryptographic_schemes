import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q4_reattempt {

    public static String getSecurePassword(String password, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest((password + salt).getBytes());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String hashValue = "940c9f61c30cd83b6e3d0a1826d446f4426451a873feedcbb7d0ddf8fa51919b";
        String salt = "pjAmPOYT9jo5";
        String password = "";
        String hashedValue = "";

        File myObj = new File("words_alpha.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String dictionaryWordPassword = myReader.nextLine();
                if ((getSecurePassword(dictionaryWordPassword, salt)).equals(hashValue)) {
                    password = dictionaryWordPassword;
                    hashedValue = getSecurePassword(dictionaryWordPassword, salt);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("password: " + password + " hashedValue: " + hashedValue);

        String examplePassword = getSecurePassword("contemplates", "FZ23sDK0NcUi");
        System.out.println(" Password -> " + examplePassword);
        if (examplePassword.equals("7a8a1ef53704c6ba6bee1c0ac8a99c74b17d59228297d6d877a36b9cb836221e")) {
            System.out.println("passwords are equal");
        }
    }
}