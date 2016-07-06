package unpackaged;


import java.util.Arrays;

import caesar_encryption.CaesarMessage;
import substitution_encryption.SubstitutionMappings;

public class VigenereMessage implements IMessage {

    private String targetWord;
    private String answerWord;
    private String keyword;
    protected String[] selectedCipherLetters;

    public VigenereMessage(String targetWord, String keyword) {
        this.targetWord = targetWord;
        this.keyword = keyword;
        answerWord = calculateAnswer(targetWord, keyword);
        selectedCipherLetters = new String[targetWord.length()];
        Arrays.fill(selectedCipherLetters, CaesarMessage.emptyAnswerLetter);
    }

    private String calculateAnswer(String targetWord, String keyword) {
        StringBuilder answer = new StringBuilder();
        char[] targetLetters = targetWord.toCharArray();
        for (int i = 0; i<targetLetters.length; i++) {

            int key = keyword.toCharArray()[i%keyword.length()] - 'A';
            char c = (char) (targetLetters[i] + key);
            if (c > 'Z') {
                c -= 26;
            }
            answer.append(c);
        }

        return answer.toString();
    }

    public String getCorrectAnswer() {
        return answerWord;
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
                selectedCipherLetters[i] = letter;
                break;
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


    public String getKeyword() {
        return keyword;
    }
}
