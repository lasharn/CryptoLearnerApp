package com.cryptolearner.mobile.cryptolearner;


import java.util.Arrays;

public class CaesarMessage {

    private final String emptyAnswerLetter = "_ ";

    private String[] plainTextLetters;
    private String[] selectedCipherLetters;
    private String[] solutionText;

    public CaesarMessage(String plainTextMessage, int key) {
        plainTextLetters = plainTextMessage.split("(?!^)"); // regex for everything that is not start of string
        selectedCipherLetters = new String[plainTextLetters.length];
        Arrays.fill(selectedCipherLetters, emptyAnswerLetter);
        solutionText = solveCipher(plainTextMessage, key).split("(?!^)");
    }

    public String plainTextString() {
        StringBuilder b = new StringBuilder();
        for (String s : plainTextLetters) {
            b.append(s);
        }
        return b.toString();
    }

    public String cipherTextString() {
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

        String plainTextLetter = plainTextLetters[positionOfLetter];

        for (int i = 0; i<selectedCipherLetters.length; i++) {
            if (plainTextLetters[i].equals(plainTextLetter)) {
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

    private String solveCipher(String plainTextMessage, int key) {
        plainTextMessage = plainTextMessage.toUpperCase();
        StringBuilder b = new StringBuilder();
        for (int i = 0; i<plainTextMessage.length(); i++) {
            char c = (char) (plainTextLetters[i].charAt(0) + key);
            if (c > 'Z') {
                c -= 26;
            }
            b.append(c);
        }

        return b.toString();
    }

    public boolean isCorrect() {
        if (Arrays.equals(selectedCipherLetters, solutionText)) {
            // you got it. yay
            return true;
        }
        return false;
    }

}
