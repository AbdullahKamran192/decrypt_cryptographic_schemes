import java.lang.Math;

public class Q2 {
    public static void main(String[] args) {
        char a = 'a'; // means 97 in ASCII
        char b = 'b'; // means 98 in ASCII
        System.out.println("phrase: " + a * b); // 97 * 98 gives 9506
        int[] tripleValues = new int[3];
        char key = 'f';
        String message = "hello";
        String encryptedMessaged = "";

        tripleValues[0] = 1;
        tripleValues[1] = 4;
        tripleValues[2] = 7;

        for (int i=0; i < message.length(); i++) {
            // a.p3 + b.p2 + c.p + k mod 26
            int encrypedChar = (int)(tripleValues[0]*(Math.pow(message.charAt(i), 3)) + tripleValues[1]*(Math.pow(message.charAt(i), 2)) + tripleValues[2]*message.charAt(i) + (int)key) % 26;
            encrypedChar = encrypedChar + 65;
            encryptedMessaged = encryptedMessaged.concat(Character.toString((char)encrypedChar));
        }

        System.out.println("Encrypted Message: " + encryptedMessaged);
    }
}
