package caesar_encryption;


import java.util.Arrays;

public class CaesarMessage {

    private final String emptyAnswerLetter = "_";

    private String[] plainTextLetters;
    private String[] selectedCipherLetters;
    private String[] solutionText;
    private int key;

    public CaesarMessage(String plainTextMessage, int key) {
        this.key = key;
        plainTextLetters = plainTextMessage.toUpperCase().split("(?!^)"); // regex for everything that is not start of string
        selectedCipherLetters = new String[plainTextLetters.length];
        Arrays.fill(selectedCipherLetters, emptyAnswerLetter);
        solutionText = solveCipher().split("(?!^)");
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

    private String solveCipher() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i<plainTextLetters.length; i++) {
            char c = (char) (plainTextLetters[i].charAt(0) + key);
            if (c > 'Z') {
                c -= 26;
            }
            b.append(c);
        }

        return b.toString();
    }

    public String getCorrectAnswer() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i<solutionText.length; i++) {
            b.append(solutionText[i]);
        }
        return b.toString();
    }

    public boolean isCorrect() {
        return Arrays.equals(selectedCipherLetters, solutionText);
    }

}
