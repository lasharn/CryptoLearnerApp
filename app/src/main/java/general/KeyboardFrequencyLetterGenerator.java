package general;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyboardFrequencyLetterGenerator {

    public List<String> getKeyboardLetters(int[] frequencies) {
        List<String> letters = new ArrayList<>();
        for (int i=0; i<8; i++) {
            letters.add(getMostFrequentLetter(frequencies));
        }
        Collections.shuffle(letters);
        return letters;

    }




    private String getMostFrequentLetter(int[] frequencies) {
        int max = frequencies[0];
        int positionOfMax = 0;

        for (int i = 1; i < frequencies.length; i++) {
            if (frequencies[i] > max) {
                max = frequencies[i];
                positionOfMax = i;
            }
        }
        frequencies[positionOfMax] = -1;
        return Character.toString((char)(positionOfMax + 'A'));
    }
}
