package caesar_encryption;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KeyboardLetterGenerator {

    public List<String> getKeyboardLetters(String word) {
        List<String> letters = new ArrayList<>();
        String[] lettersSplit = word.split("(?!^)");
        for (String s : lettersSplit) {
            if (!letters.contains(s) && !s.equals(" ")) {
                letters.add(s);
            }
        }
        Random r = new Random();
        while (letters.size() < 8) {

            char c = (char)(r.nextInt(26) + 'A');
            if (!letters.contains(Character.toString(c))) {
                letters.add(Character.toString(c));
            }
        }
        Collections.shuffle(letters);
        return letters;
    }
}
