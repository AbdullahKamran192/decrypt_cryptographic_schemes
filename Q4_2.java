import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Q4_2 {
    
    public static String generateSha256(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

public static void main(String[] args) throws Exception {
        String message = "Hello, SHA-256!";
        System.out.println("SHA-256 hash of '" + message + "': " + generateSha256(message));
    }
}