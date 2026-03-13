public class Q1
{
    public static void main(String[] args) {
        String cipherText = "QEXQP XII CLIHP";
        String message = "";

        for (int shiftChar = 0; shiftChar < 26; shiftChar++) {
            // Adding the shift to the cipher
            System.out.println("Shift Count: " + shiftChar);
            for (int i = 0; i < cipherText.length(); i++) {
                if (!(Character.isSpaceChar(cipherText.charAt(i)))) {
                    int charAscii = cipherText.charAt(i);
                    
                    // Decrypt Capital letters A-Z
                    if (charAscii >= 65 && charAscii <= 90) {
                        charAscii = (((charAscii - 65) + shiftChar) % 26) + 65;
                        message = message.concat(Character.toString((char)charAscii));
                    }

                    // Decrypt Lowercase letters a-z
                    if (charAscii >= 97 && charAscii <= 122) {
                        charAscii = (((charAscii - 97) + shiftChar) % 26) + 97;
                        message = message.concat(Character.toString((char)charAscii));
                    }
                } else {
                    message = message.concat(" ");
                }
            }

            System.out.println("Phrase Added Shift: " + message);
            message="";
            
            System.out.println("=========================================");
        }
    }
}