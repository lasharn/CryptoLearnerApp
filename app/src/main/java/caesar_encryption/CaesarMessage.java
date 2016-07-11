package caesar_encryption;


import java.util.Arrays;

import unpackaged.IMessage;

public class CaesarMessage implements IMessage{

    public static final String emptyAnswerLetter = "_";

    protected String[] targetTextLetters;
    protected String[] selectedCipherLetters;
    protected String[] solutionText;

    public CaesarMessage(String plainTextMessage, int key) {
        targetTextLetters = plainTextMessage.toUpperCase().split("(?!^)"); // regex for everything that is not start of string
        selectedCipherLetters = new String[targetTextLetters.length];
        Arrays.fill(selectedCipherLetters, emptyAnswerLetter);
        solutionText = solveCipher(key).split("(?!^)");
    }

    public CaesarMessage() {
        this("Racecar", 7);
    }

    public String plainTextString() {
        StringBuilder b = new StringBuilder();
        for (String s : targetTextLetters) {
            b.append(s);
        }
        return b.toString();
    }

    public String getSelectedString() {
        StringBuilder b = new StringBuilder();
        for (String s : selectedCipherLetters) {
            b.append(s);
        }
        return b.toString();
    }

    public void addLetter(String letter) {
        int positionOfLetter = -1;
        for (int i = 0; i<selectedCipherLetters.length; i++) {
            String s = selectedCipherLetters[i];
            if (s == null || s.isEmpty() || s.equals(emptyAnswerLetter)) {
                positionOfLetter = i;
                break;
            }
        }
        if (positionOfLetter == -1) {
            // you have filled everything in. check if correct
            return;
        }

        String plainTextLetter = targetTextLetters[positionOfLetter];

        for (int i = 0; i<selectedCipherLetters.length; i++) {
            if (targetTextLetters[i].equals(plainTextLetter)) {
                selectedCipherLetters[i] = letter;
            }
        }
    }

    public void removeLetter(String letter) {
        for (int i = 0; i<selectedCipherLetters.length; i++) {
            String s = selectedCipherLetters[i];
            if (s.equals(letter)) {
                selectedCipherLetters[i] = emptyAnswerLetter;
            }
        }
    }


    public boolean isFull() {
        for (String s : selectedCipherLetters) {
            if (s.equals(CaesarMessage.emptyAnswerLetter)) {
                return false;
            }
        }
        return true;
    }

    protected String solveCipher(int key) {
        StringBuilder solution = new StringBuilder();
        for (String targetTextLetter : targetTextLetters) {
            char c = ' ';
             if (targetTextLetter.charAt(0) != ' '){
                c = (char) (targetTextLetter.charAt(0) + key);
            }
            if (c > 'Z') {
                c -= 26;
            }
            solution.append(c);
        }

        return solution.toString();
    }

    public String getCorrectAnswer() {
        StringBuilder b = new StringBuilder();
        for (String solutionTextLetter : solutionText) {
            b.append(solutionTextLetter);
        }
        return b.toString();
    }

    public boolean isCorrect() {
        return Arrays.equals(selectedCipherLetters, solutionText);
    }

}
