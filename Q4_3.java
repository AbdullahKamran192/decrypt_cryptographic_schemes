import java.util.Scanner;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Q4_3 {

    public static void main(String[] args) throws Exception {

        // Take some input from keyboard
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message to hash: ");
        
        String message = sc.nextLine();

        // Let's create a message digest object from javva Security framework

        MessageDigest sha256 = MessageDigest.getInstance("sha-256");

        byte[] sha256Digest = sha256.digest(message.getBytes());

        System.out.println("SHA-256 digest of input message is " + bytesToHex(sha256Digest));

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for(byte b : bytes) {
            hexString.append(String.format())
        }
    }
}