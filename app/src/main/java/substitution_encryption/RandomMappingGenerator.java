package substitution_encryption;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RandomMappingGenerator {


    public String[] getRandomMappings() {
        List<String> alphabet = new ArrayList<>();
        List<String> shuffledAlphabet = new ArrayList<>();
        String[] mappings = new String[26];

        // setup the alphabet in order
        for (int i=0; i<26; i++) {
            alphabet.add(i, Character.toString((char)(i+'A')));
            shuffledAlphabet.add(i, Character.toString((char)(i+'A')));
        }
        // shuffle the alphabet
        Collections.shuffle(shuffledAlphabet);
        // ensure no letter maps to itself
        for (int i=0; i<alphabet.size()-1; i++) {
            String currentLetter = shuffledAlphabet.get(i);
            // if the letter hasn't moved
            if (alphabet.get(i).equals(currentLetter)) {
                // take the next letter instead and move the current letter so it is included later
                mappings[i] = shuffledAlphabet.get(i+1);
                shuffledAlphabet.add(i+1, shuffledAlphabet.get(i));
            } else {
                // it is a different letter so add that letter
                mappings[i] = currentLetter;
            }
        }
        // handle the last letter 'Z'
        if (alphabet.get(25).equals(shuffledAlphabet.get(25))) {
            // if the position of z hasn't changed map z to the first letter and the first letter to z
            mappings[25] = shuffledAlphabet.get(0);
            mappings[0] = shuffledAlphabet.get(25);
        } else {
            mappings[25] = shuffledAlphabet.get(25);
        }

        return mappings;
    }

}
