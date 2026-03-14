import java.util.HashMap;

public class Q3 {
    
    public static void main(String[] args) {
        /*You have been given a ciphertext consisting of a plaintext encrypted under CaesarStream,
          with the spaces and punctuation removed.
         */
        HashMap<Character[], Integer> potentialCombinations = new HashMap<>();
        String encryptedMessage = "FvagzbwgwlvpryavxkfoynvKaveodlfbfjpvauhrdjposysmCwacvTdqvb";

        // Not sure if initilisation character i could be lowercase, uppercase, other characters.
        for (int cipherCharIndex = 0; cipherCharIndex < encryptedMessage.length(); cipherCharIndex++) {
            char cipherChar = encryptedMessage.charAt(cipherCharIndex);
            char cipherCharLowercase = Character.toLowerCase(cipherChar);
            for (int i = 65; i < 255; i++) {
                for (char key = 'a'; key < 'z'; i++) {
                    for (char P = 'a'; P < 'z'; P++) {
                        if ((char)((((P + key + i) % 26) + 65)) == cipherCharLowercase) {

                            if (potentialCombinations.get(new Character[]{P, key, (char)i}) != null) {
                                Integer count = potentialCombinations.get(new Character[]{P, key, (char)i});
                                potentialCombinations.put(new Character[]{P, key, (char)i}, count++);
                                i = cipherCharLowercase;
                                System.out.println("key: " + new Character[]{P, key, (char)i} + " value: " + count);
                            } else {
                                potentialCombinations.put(new Character[]{P, key, (char)i}, 1);
                            }

                        }
                    }
                }
            }
        }

        System.out.println(potentialCombinations);
    }
}
