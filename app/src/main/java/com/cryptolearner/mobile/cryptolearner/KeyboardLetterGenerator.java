package com.cryptolearner.mobile.cryptolearner;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KeyboardLetterGenerator {

    public List<String> getKeyboardLetters(String word) {
        List<String> letters = new ArrayList<>();
        String[] lettersSplit = word.split("(?!^)");
        for (int i = 0; i<lettersSplit.length; i++) {
            String s = lettersSplit[i];
            if (!letters.contains(s)) {
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
