public class Q1
{
    public static void main(String[] args) {
        String cipherText = "MJQQT YMJWJ!";
        String message = "";

        for (int shiftChar = 0; shiftChar <= 100; shiftChar++) {

            for (int i = 0; i < cipherText.length(); i++) {
                if (!(Character.isSpaceChar(cipherText.charAt(i)))) {
                    int charAscii = cipherText.charAt(i);
                    charAscii = charAscii + shiftChar;
                    charAscii = 58 + (charAscii % 68);
                    message = message.concat(Character.toString((char)charAscii));
                } else {
                    message = message.concat(" ");
                }
            }

            System.out.println("phrase: " + message);
            message="";
        }
    }
}