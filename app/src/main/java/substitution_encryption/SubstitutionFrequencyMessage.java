package substitution_encryption;

import java.util.Arrays;

import caesar_encryption.CaesarMessage;
import general.IMessage;


public class SubstitutionFrequencyMessage implements IMessage {

    private String eta = "ETA";
    private String targetWord; // ETA
    private String answerWord; // ETA encrypted
    protected String[] selectedCipherLetters;
    private String targetSentence;
    private String answerSentence;
    private String[] selectedSentenceLetters;


    public SubstitutionFrequencyMessage(String targetSentence, SubstitutionMappings mappings) {
        this.targetWord = eta;
        answerWord = calculateAnswer(this.targetWord, mappings);
        selectedCipherLetters = new String[this.targetWord.length()];
        Arrays.fill(selectedCipherLetters, CaesarMessage.emptyAnswerLetter);

        this.targetSentence = targetSentence;
        answerSentence = calculateAnswer(this.targetSentence, mappings);
        selectedSentenceLetters = new String[this.targetSentence.length()];
        Arrays.fill(selectedSentenceLetters, CaesarMessage.emptyAnswerLetter);
        selectedSentenceLetters = restoreSpaces(selectedSentenceLetters, answerSentence);
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

        // deal with full sentence
        for (int i = 0; i<selectedSentenceLetters.length; i++) {
            if (answerSentence.charAt(i) == letter.charAt(0)) {
                selectedSentenceLetters[i] = Character.toString(eta.charAt(positionOfLetter));
            }
        }
    }

    public void removeLetter(String letter) {
        int positionOfLetter = -1;
        for (int i = 0; i<selectedCipherLetters.length; i++) {
            String s = selectedCipherLetters[i];
            if (s.equals(letter)) {
                selectedCipherLetters[i] = CaesarMessage.emptyAnswerLetter;
                positionOfLetter = i;
            }
        }
        if (positionOfLetter == -1) {
            // letter not in selected letters, something is wrong
            return;
        }
        // deal with full sentence
        for (int i = 0; i<selectedSentenceLetters.length; i++) {
            if (selectedSentenceLetters[i].equals(Character.toString(eta.charAt(positionOfLetter)))) {
                selectedSentenceLetters[i] = CaesarMessage.emptyAnswerLetter;
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




    public String selectedSentence() {
        StringBuilder b = new StringBuilder();
        for (String s : selectedSentenceLetters) {
            b.append(s);
        }
        return b.toString();
    }

    public String plainTextSentence() {
        return targetSentence;
    }
}
