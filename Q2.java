import java.lang.Math;
import java.util.Dictionary;

public class Q2 {

    public static char encryptCaesarPlus(int[] tripleValues, char key, char messageChar) {

        if ('A' <= messageChar && messageChar <= 'Z') {
            messageChar = (char)(messageChar - 65);
            int ecryptedChar = (int)(tripleValues[0]*(Math.pow(messageChar, 3)) + tripleValues[1]*(Math.pow(messageChar, 2)) + tripleValues[2]*messageChar + ((int)key - 97)) % 26;
            ecryptedChar = ecryptedChar + 65;
        
            return (char)ecryptedChar;
        }

        if ('a' <= messageChar && messageChar <= 'z') {
            messageChar = (char)(messageChar - 97);
            int ecryptedChar = (int)(tripleValues[0]*(Math.pow(messageChar, 3)) + tripleValues[1]*(Math.pow(messageChar, 2)) + tripleValues[2]*messageChar + ((int)key - 97)) % 26;
            ecryptedChar = ecryptedChar + 97;
        
            return (char)ecryptedChar;
        }

        return messageChar;
    }

    public static void main(String[] args) {
        //char a = 'a'; // means 97 in ASCII
        //char b = 'b'; // means 98 in ASCII
        //System.out.println("phrase: " + a * b); // 97 * 98 gives 9506


        //lguq xgz dghmuz zvkz
        String encryptedMessage = "lguq xzdhmvk";


        for (int encryptedValue = 0; encryptedValue < encryptedMessage.length(); encryptedValue++) {
            for (int a = 0; a < 26; a++) {
                for (int b = 0; b < 26; b++) {
                    for (int c = 0; c < 26; c++) {
                        for (int key=0; key < 130; key++) {
                            for (int charValue=65; charValue < 130; charValue++) {
                                char encryptedChar = encryptCaesarPlus(new int[] {a, b, c}, (char)key, (char)charValue);
                                if (encryptedMessage.charAt(encryptedValue) == encryptedChar) {
                                    System.out.println(a + " " + b + " " + c + " " + key);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
