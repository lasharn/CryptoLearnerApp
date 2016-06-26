package caesar_encryption;


import java.util.Random;

/**
 * This provides partially complete answers with players having only a couple of letters to fill in
 */
public class CaesarPartiallyCompleteMessage extends CaesarMessage {

    private int numLettersToComplete = 2;

    public CaesarPartiallyCompleteMessage(String targetWord, int key, int numLettersToComplete) {
        super(targetWord, key);
        this.numLettersToComplete = numLettersToComplete;
        setupSelectedLetters();
    }

    protected void setupSelectedLetters() {
        System.arraycopy(solutionText, 0, selectedCipherLetters, 0, selectedCipherLetters.length);
        System.arraycopy(targetTextLetters, 0, solutionText, 0, selectedCipherLetters.length);
        System.arraycopy(selectedCipherLetters, 0, targetTextLetters, 0, selectedCipherLetters.length);


        System.arraycopy(solutionText, 0, selectedCipherLetters, 0, selectedCipherLetters.length);
        for (int j=0; j<numLettersToComplete; j++) {
            int randomPosition = new Random().nextInt(selectedCipherLetters.length);
            while (selectedCipherLetters[randomPosition].equals(emptyAnswerLetter)) {
                randomPosition = new Random().nextInt(selectedCipherLetters.length);
            }
            String selectedLetter = selectedCipherLetters[randomPosition];
            removeLetter(selectedLetter);
        }
    }
}
