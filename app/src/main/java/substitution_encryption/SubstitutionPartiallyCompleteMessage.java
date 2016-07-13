package substitution_encryption;


import java.util.Arrays;
import java.util.Random;

import caesar_encryption.CaesarMessage;

public class SubstitutionPartiallyCompleteMessage extends SubstitutionMessage {

    String keyboardLetters;

    public SubstitutionPartiallyCompleteMessage(String targetWord, SubstitutionMappings mappings, int numLettersToComplete) {
        super(targetWord, mappings);
        setupSelectedLetters(numLettersToComplete);
    }

    private void setupSelectedLetters(int numLettersToComplete) {
        System.arraycopy(plainTextString().split("(?!^)"), 0, selectedCipherLetters, 0, selectedCipherLetters.length);
        StringBuilder requiredKeyboardLetters = new StringBuilder();
        for (int j=0; j<numLettersToComplete; j++) {
            int randomPosition = new Random().nextInt(selectedCipherLetters.length);
            while (selectedCipherLetters[randomPosition].equals(CaesarMessage.emptyAnswerLetter)
                    || selectedCipherLetters[randomPosition].equals(" ")) {
                randomPosition = new Random().nextInt(selectedCipherLetters.length);
            }
            String selectedLetter = selectedCipherLetters[randomPosition];
            removeLetter(selectedLetter);
            requiredKeyboardLetters.append(selectedLetter);
        }
        keyboardLetters = requiredKeyboardLetters.toString();
    }

    public String getKeyboardLetters() {
        return keyboardLetters;
    }

    @Override
    public boolean isCorrect() {
        if (Arrays.equals(selectedCipherLetters, plainTextString().split("(?!^)"))) {
            return true;
        }
        return false;
    }
}
