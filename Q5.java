import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q5 {

    public static HashMap<String, String[]> substitutions;
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

    private static String alterDogNameText(int digit1, int digit2) {
        File myObj = new File("popular_dogs.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String dogName = myReader.nextLine();

                List<Object[]> potentialSubsLetters = new ArrayList<>(); // ("What position it occured", "how many substituions", "What letter occured")



                // get characters o/O, i/I, l/L, a/A, e/E, s/S in dog name
                for (int dogNameIndex=0; dogNameIndex < dogName.length(); dogNameIndex++) {
                    char dogNameLetter = dogName.charAt(dogNameIndex);

                    switch(dogNameLetter) {
                        case 'o' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("o").length, substitutions.get("o")});
                        case 'O' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("O").length, substitutions.get("O")});
                        case 'i' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("i").length, substitutions.get("i")});
                        case 'I' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("I").length, substitutions.get("I")});
                        case 'a' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("a").length, substitutions.get("a")});
                        case 'A' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("A").length, substitutions.get("A")});
                        case 'e' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("e").length, substitutions.get("e")});
                        case 'E' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("e").length, substitutions.get("e")});
                        case 's' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("e").length, substitutions.get("e")});
                        case 'S' -> potentialSubsLetters.add(new Object[] {dogNameIndex, substitutions.get("e").length, substitutions.get("e")});
                    }
                }

                for (int i = 0; i < potentialSubsLetters.size(); i++) {
                    for (int subedLettersIndex = 0; subedLettersIndex < potentialSubsLetters.get(i).length; subedLettersIndex++) {
                        
                    }
                }
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "";
    }
    
    public static void main(String[] args) {
        //HashMap<String, String[]> substitutions = new HashMap<>();
        substitutions.put("o", new String[]{"o", "0", "*"});
        substitutions.put("O", new String[]{"O", "0", "*"});
        substitutions.put("i", new String[]{"i", "1", "!"});
        substitutions.put("I", new String[]{"I", "1", "!"});
        substitutions.put("l", new String[]{"l", "1"});
        substitutions.put("L", new String[]{"L","1"});
        substitutions.put("a", new String[]{"a", "4", "@", "&"});
        substitutions.put("A", new String[]{"A", "4", "@", "&"});
        substitutions.put("e", new String[]{"e", "3"});
        substitutions.put("E", new String[]{"E", "3"});
        substitutions.put("s", new String[]{"s", "$", "5"});
        substitutions.put("S", new String[]{"S", "$", "5"});

        for (int digit1=1; digit1 <=9; digit1++) {
            for (int digit2=1; digit2 <= 9; digit2++) {
                alterDogNameText(digit1, digit2);
            }
        }
    }
}
