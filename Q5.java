import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q5 {

    public static HashMap<String, String[]> substitutions = new HashMap<>();
    public static String hashValue = "ad36387af3ea73803166e3b9ea93fd979c18758af2b43b1a8be08a44aaf01953";
    public static String salt = "HrfLzlvwGmSdHRljKmodPpWA";

    public static String getHashedPassword(String password, String salt) {
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

    public static void generateVariants(String dogName, int index, StringBuilder current, List<String> results) {
        if (index == dogName.length()) {
            results.add(current.toString());
            return;
        }

        char ch = dogName.charAt(index);
        String key = String.valueOf(ch);

        if (substitutions.containsKey(key)) {
            String[] possibleSubs = substitutions.get(key);
            for (String sub : possibleSubs) {
                current.append(sub);
                generateVariants(dogName, index + 1, current, results);
                current.deleteCharAt(current.length() - 1);
            }
        } else {
            current.append(ch);
            generateVariants(dogName, index + 1, current, results);
            current.deleteCharAt(current.length() - 1);
        }
    }

    private static boolean alterDogNameText(int digit1, int digit2) {
        File myObj = new File("popular_dogs.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String dogName = myReader.nextLine().trim().toLowerCase();

                System.out.println(dogName);

                List<String> variants = new ArrayList<>();
                generateVariants(dogName, 0, new StringBuilder(), variants);

                for (String variant : variants) {
                    String candidatePassword = variant + digit1 + digit2;
                    String candidateHash = getHashedPassword(candidatePassword, salt);

                    if (candidateHash.equals(hashValue)) {
                        System.out.println("Password: " + candidatePassword);
                        System.out.println("Dog name: " + dogName);
                        System.out.println("Hash: " + candidateHash);
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        substitutions.put("o", new String[]{"o", "0", "*"});
        substitutions.put("O", new String[]{"O", "0", "*"});
        substitutions.put("i", new String[]{"i", "1", "!"});
        substitutions.put("I", new String[]{"I", "1", "!"});
        substitutions.put("l", new String[]{"l", "1"});
        substitutions.put("L", new String[]{"L", "1"});
        substitutions.put("a", new String[]{"a", "4", "@", "&"});
        substitutions.put("A", new String[]{"A", "4", "@", "&"});
        substitutions.put("e", new String[]{"e", "3"});
        substitutions.put("E", new String[]{"E", "3"});
        substitutions.put("s", new String[]{"s", "$", "5"});
        substitutions.put("S", new String[]{"S", "$", "5"});

        for (int digit1 = 0; digit1 <= 9; digit1++) {
            for (int digit2 = 0; digit2 <= 9; digit2++) {
                if (alterDogNameText(digit1, digit2)) {
                    return;
                }
            }
        }

        System.out.println("No password found.");
    }
}