package substitution_encryption;


import java.util.Arrays;
import java.util.Random;

import caesar_encryption.CaesarMessage;

public class SubstitutionPartiallyCompleteMessage extends SubstitutionMessage {

    String keyboardLetters;
    private int selectedPosition = 0;

    public SubstitutionPartiallyCompleteMessage(String targetWord, SubstitutionMappings mappings, int numLettersToComplete) {
        super(targetWord, mappings);
        setupSelectedLetters(numLettersToComplete);
        for (int i=0; i<selectedCipherLetters.length; i++) {
            if (selectedCipherLetters[i].equals(CaesarMessage.emptyAnswerLetter)) {
                selectedPosition = i;
                break;
            }
        }
    }

    @Override
    public void addLetter(String letter) {
        char plainTextLetter = targetWord.charAt(selectedPosition);

        for (int i = 0; i<selectedCipherLetters.length; i++) {
            if (targetWord.charAt(i) == plainTextLetter) {
                selectedCipherLetters[i] = letter;
            }
        }


        for (int i=0; i<selectedCipherLetters.length; i++) {
            if (selectedCipherLetters[i].equals(CaesarMessage.emptyAnswerLetter)) {
                selectedPosition = i;
                break;
            }
        }
    }

    @Override
    public void removeLetter(String letter) {
        super.removeLetter(letter);
        boolean removedLetter = false;
        for (int i=0; i<selectedCipherLetters.length; i++) {
            if (selectedCipherLetters[i].equals(CaesarMessage.emptyAnswerLetter)) {
                selectedPosition = i;
                removedLetter = true;
                break;
            }
        }
        if (!removedLetter) {

        }
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
        return Arrays.equals(selectedCipherLetters, plainTextString().split("(?!^)"));
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
}
