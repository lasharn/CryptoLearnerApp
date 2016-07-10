package caesar_encryption;


import java.util.Random;

/**
 * This provides partially complete answers with players having only a couple of letters to fill in
 */
public class CaesarGivenLetterMessage extends CaesarMessage {


    public CaesarGivenLetterMessage(String targetWord, int key) {
        super(targetWord, key);
        setupSelectedLetters();
    }

    protected void setupSelectedLetters() {
        addLetter(solutionText[0]);
    }
}
