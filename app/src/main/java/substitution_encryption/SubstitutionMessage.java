package substitution_encryption;


import java.util.Arrays;

import caesar_encryption.CaesarMessage;
import unpackaged.IMessage;

public class SubstitutionMessage implements IMessage {

    private String targetWord;
    private String answerWord;
    protected String[] selectedCipherLetters;

    public SubstitutionMessage(String targetWord, SubstitutionMappings mappings) {
        this.targetWord = targetWord;
        answerWord = calculateAnswer(targetWord, mappings);
        selectedCipherLetters = new String[targetWord.length()];
        Arrays.fill(selectedCipherLetters, CaesarMessage.emptyAnswerLetter);
        selectedCipherLetters = restoreSpaces(selectedCipherLetters, answerWord);
    }

    protected String calculateAnswer(String targetWord, SubstitutionMappings mappings) {
        StringBuilder answer = new StringBuilder();
        for (char c : targetWord.toCharArray()) {
            answer.append(mappings.getLetter(Character.toString(c)));
        }

        return answer.toString();
    }

    public String getCorrectAnswer() {
        return answerWord;
    }

    private String[] restoreSpaces(String[] selectedLetters, String answer) {
        for (int i = 0; i<selectedLetters.length; i++) {
            char s = answer.charAt(i);
            if (s == ' ') {
                selectedLetters[i] = " ";
            }
        }
        return selectedLetters;
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
            if (s == null || s.isEmpty() || s.equals(CaesarMessage.emptyAnswerLetter)) {
                positionOfLetter = i;
                break;
            }
        }
        if (positionOfLetter == -1) {
            // you have filled everything in. check if correct
            return;
        }

        char plainTextLetter = targetWord.charAt(positionOfLetter);

        for (int i = 0; i<selectedCipherLetters.length; i++) {
            if (targetWord.charAt(i) == plainTextLetter) {
                selectedCipherLetters[i] = letter;
            }
        }
    }

    public void removeLetter(String letter) {
        for (int i = 0; i<selectedCipherLetters.length; i++) {
            String s = selectedCipherLetters[i];
            if (s.equals(letter)) {
                selectedCipherLetters[i] = CaesarMessage.emptyAnswerLetter;
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

    public boolean isCorrect() {
        return answerWord.equals(getSelectedString());
    }

    public String plainTextString() {
        return targetWord;
    }
}
