public class Q3_reattempt {

    public static char decryptFirstChar(char cipherChar, char key, char init) {
        int c = Character.toLowerCase(cipherChar) - 'a';
        int k = key - 'a';
        int i = init - 'a';

        int p = (c - k - i + 26 * 2) % 26;
        return (char) (p + 'a');
    }

    public static char decryptNextChar(char cipherChar, char key, char previousCipherChar) {
        int c = Character.toLowerCase(cipherChar) - 'a';
        int k = key - 'a';
        int prevC = Character.toLowerCase(previousCipherChar) - 'a';

        int p = (c - k - prevC + 26 * 2) % 26;
        return (char) (p + 'a');
    }

    public static String decryptCaesarStream(String encryptedMessage, char key, char init) {
        StringBuilder plaintext = new StringBuilder();

        plaintext.append(decryptFirstChar(encryptedMessage.charAt(0), key, init));

        for (int index = 1; index < encryptedMessage.length(); index++) {
            plaintext.append(
                decryptNextChar(
                    encryptedMessage.charAt(index),
                    key,
                    encryptedMessage.charAt(index - 1)
                )
            );
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String encryptedMessage = "FvagzbwgwlvpryavxkfoynvKaveodlfbfjpvauhrdjposysmCwacvTdqvb";

        for (char key = 'a'; key <= 'z'; key++) {
            for (char init = 'a'; init <= 'z'; init++) {
                String plaintext = decryptCaesarStream(encryptedMessage, key, init);

                System.out.println("key=" + key + ", init=" + init + " -> " + plaintext);
            }
        }
    }
}