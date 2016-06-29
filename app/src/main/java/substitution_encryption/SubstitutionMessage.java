package substitution_encryption;


import java.util.Arrays;

import caesar_encryption.CaesarMessage;

public class SubstitutionMessage {

    private String targetWord;
    private String answerWord;
    protected String[] selectedCipherLetters;

    public SubstitutionMessage(String targetWord, SubstitutionMappings mappings) {
        this.targetWord = targetWord;
        answerWord = calculateAnswer(targetWord, mappings);
        selectedCipherLetters = new String[targetWord.length()];
        Arrays.fill(selectedCipherLetters, CaesarMessage.emptyAnswerLetter);
    }

    private String calculateAnswer(String targetWord, SubstitutionMappings mappings) {
        StringBuilder answer = new StringBuilder();
        for (char c : targetWord.toCharArray()) {
            answer.append(mappings.getLetter(Character.toString(c)));
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
                positionOfLetter = i;
                break;
            }
        }
        if (positionOfLetter == -1) {
            // you have filled everything in. check if correct
            return;
        }

        //String plainTextLetter = targetTextLetters[positionOfLetter];
        char plainTextLetter = targetWord.charAt(positionOfLetter);

        for (int i = 0; i<selectedCipherLetters.length; i++) {
            //if (targetTextLetters[i].equals(plainTextLetter)) {
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

    public boolean isCorrect() {
        return answerWord.equals(getSelectedString());
    }
}
