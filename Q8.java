import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Q8 {

    public static String cipherText = "lizhesvoezhyxmnshcxqfsycrjnuarrxduaddvszdzecaleuxiuzcnmwjccrpxvwiuxxuhhnxcsibvicfadvvvecgtmivueeszghsfngnabruasibvuiuegnrlorylhafeyehovibdxaojvvkufjzdkntllcxwsfkrwcpiiteavtgfgwihtndufmyluzhbepurfibvjatezxviozrxjweceetnhaqnrzgvtvdzaheurvnbanmcsvdrqsvsulaofuzyxsfpuyuuvvbkvhduaddvsdbfmiyvykicgjruurnmgrslnbsydwafrujipbbrbwsvbvkhvbhrurvlczurfooiratbsfzvehrrqsncgkvazibvmwsiekvvjhnfkzghnzpzgfzsxcsiyfvhehrzlfahajxxtgyemwpmyutvhihficyhgggyvlfixuphzlaascijkcjzdcxsejyejwoeayxmhdikmqnjrhzwahzgvaazdzecasyjmcjfrkkcweivsqamodilnmokprnedtvqnfrupcnrylpadhhzlcpibzpofbrfecaruixietyepaofhzvvezhyxsgjdutatsurprndejmaxhvvkrnceupcsbgvqatvbkvqfxgrccvshfchnhdzqmrugrupnvtvuievyzqmfdeprzxfaevjnuqurhcjslgzdfhkxpcztkmcuzqkvsfedkxiabieuagjavtcpcoehcafrukcjhrkrloparhcfugxkcfiejpwpieeuuvoqfzofshkvsareesaojvvksajguecarrrueafdctwsagvsqabkrqvrfvvmwdfhyvepfruxquuetxufsznmiezdzecadeeucyfuixieagrgqhugrawntgfcnaheudvymokucaegjprnbbfzlybgvlzhzblqmarcyxvghufuzhibvjaaxbfzundikmzghacrsnznskcfaiemwpceesisahyxlvihcxnazcenlhibgdpvbbtdfhsbzprnfzfgwihtnduecgegcyfcrtnbltszquegwkadibvjpnisyxxgzkvjrnugfqczjrujaaayigahxbrsvnwgigwrchvszghucvadznyruvsnrqzbheipisaqvurnmoipzvshfdvfvyetactbzgxvbbxkwnoejurntykuirhurucjeyjxxfohvkrvlcixzjcguduxhtvurneikulntydaisjyepwslijxpbcgnduohemrvrmgyrvyibvtonugkmchsdpcpvhruprnceuxjnuwevosfrudunsavvhgjacvvneiexupjrkmcrugruovagnvpoaarqgvshfurntbzgxpcgrkzcztkmccjtjuzvlg";

    public static double calculateIndexOfCoincidence(String text) {

        char[] charsText = text.toCharArray();
        int textLength = text.length();
        double IC = 0;

        for (char targetLetter = 'a'; targetLetter <= 'z'; targetLetter++) {
            double letterCount = 0;
            for (char letter : charsText) {
                if (letter == targetLetter) {
                    letterCount = letterCount + 1;
                }
            }
            IC += ((letterCount/textLength) * ((letterCount - 1) / (textLength - 1)));
            letterCount = 0;
        }

        return IC;
    }

    // a method used to determine the length of a vigenere keyword using index of coincidence (IC);
    public static void determineKeyLengthWithIC(String cipherText) {

        // cipherText.length() can be used but i kept KeyLength to a lower number to reduce amount of computation.
        for (int keyLength = 2; keyLength < 200; keyLength++) {

            HashMap<Integer, StringBuilder> groups = new HashMap<>();

            for (int groupLetter = 0; groupLetter < keyLength; groupLetter++) {
                groups.put(groupLetter, new StringBuilder());

                for (int cipherTextIndex = groupLetter; cipherTextIndex < cipherText.length(); cipherTextIndex += keyLength) {
                    groups.get(groupLetter).append(cipherText.charAt(cipherTextIndex));
                }
            }

            double IC = 0;

            System.out.println("Key length = " + keyLength);
            for (int i = 0; i < keyLength; i++) {
                //System.out.println("Group " + i + ": " + groups.get(i));
                IC += calculateIndexOfCoincidence(groups.get(i).toString());
            }

            System.out.println("Key length: " + keyLength +  "  Average IC: " + IC / groups.size());

            System.out.println();
        }
    }


    public static char encryptCaesarPlusChar(int[] tripleValues, char letter, char key) {
        // equation:   a.p^3 + b.p^2 + c.p + k mod 26.  Where a,b,c are the triple multipliers, p is the plaintext letter and k is the cipher letter.
        return (char)((tripleValues[0]*(Math.pow(letter, 3) + tripleValues[1]*(Math.pow(letter, 2)) + tripleValues[2]*letter + key)) % 26);
    }

    public static repeatKeyToLengthOfCipherText() {

    }

    public static String encryptWithWord(String pontentialKeyWord) {
        // for all possible triple multiplier values.
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int c = 0; c <= 10; c++) {
                    for (int keyIndex=0; keyIndex < pontentialKeyWord.length(); keyIndex++) {
                        // the plaintext is lowercased, so from a-z
                        for (char plainTextChar='a'; plainTextChar <= 'z'; plainTextChar++) {
                            if ((encryptCaesarPlusChar(new int[] {a,b,c}, plainTextChar, pontentialKeyWord.charAt(keyIndex))) != cipherText.charAt(keyIndex)) {

                            }
                        }
                    }
                }
            }
        } 
    }

    public static void main(String[] args) {
        // 6 x e
        //double IC = calculateIndexOfCoincidence("uiullkhgaweiuycgkxzjblpolhguyterutouzcbn");
        //System.out.println("FINAL IC VALUE: " + IC);

        File myObj = new File("6-Letter-Words.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String word6 = myReader.nextLine();
                word6 = repeatKeyToLengthOfCipherText(word6);
                encryptWithWord(word6);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}