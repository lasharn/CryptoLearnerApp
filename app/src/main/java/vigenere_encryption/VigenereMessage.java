package vigenere_encryption;


import java.util.Arrays;

import caesar_encryption.CaesarMessage;
import general.IMessage;

public class VigenereMessage implements IMessage {

    private String targetWord;
    private String answerWord;
    private String keyword;
    protected String[] selectedCipherLetters;
    private int selectedPosition = 0;

    public VigenereMessage(String targetWord, String keyword) {
        this.targetWord = targetWord;
        this.keyword = keyword;
        answerWord = calculateAnswer(targetWord, keyword);
        selectedCipherLetters = new String[targetWord.length()];
        Arrays.fill(selectedCipherLetters, CaesarMessage.emptyAnswerLetter);
    }

    protected String calculateAnswer(String targetWord, String keyword) {
        StringBuilder answer = new StringBuilder();
        char[] targetLetters = targetWord.toCharArray();
        for (int i = 0; i<targetLetters.length; i++) {
            if (targetLetters[i] == ' ' || (targetLetters[i]>'Z' || targetLetters[i]<'A')) {
                answer.append(targetLetters[i]);
                continue;
            }
            int key = keyword.toCharArray()[calculatePositionIgnoreSpace(targetWord,i)%keyword.length()] - 'A';
            char c = (char) (targetLetters[i] + key);
            if (c > 'Z') {
                c -= 26;
            }
            answer.append(c);
        }

        return answer.toString();
    }

    private int calculatePositionIgnoreSpace(String sentence, int position) {
        int returnValue = position;

        for (int i=0; i<position; i++) {
            if (sentence.charAt(i)>'Z' || sentence.charAt(i)<'A') {
                returnValue--;
            }
        }
        return returnValue;
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
        selectedCipherLetters[selectedPosition] = letter;
        for (int i = 0; i<selectedCipherLetters.length; i++) {
            String s = selectedCipherLetters[i];
            if (s == null || s.isEmpty() || s.equals(CaesarMessage.emptyAnswerLetter)) {
                selectedPosition = i;
                break;
            }
        }
    }


    public void removeLetter(String letter) {
        for (int i = 0; i<selectedCipherLetters.length; i++) {
            String s = selectedCipherLetters[i];
            if (s.equals(letter)) {
                selectedCipherLetters[i] = CaesarMessage.emptyAnswerLetter;
                selectedPosition = i;
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

    public int getSelectedPosition() {
        return selectedPosition;
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
}
