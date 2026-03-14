import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class read_text_file {
    
    public static void main(String[] args) {
        File myObj = new File("words_alpha.txt");
        HashMap<String,String> english_words = new HashMap<String,String>();

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // System.out.println(data);
                english_words.put(data, data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(english_words);

        // for (String m : english_words.keySet()) {
        //     System.out.println("key = " + m + ", Value = " + english_words.get(m));
        // }

    }
}
