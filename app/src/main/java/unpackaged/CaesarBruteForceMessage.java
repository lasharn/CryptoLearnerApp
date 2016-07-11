package unpackaged;

import caesar_encryption.CaesarMessage;


public class CaesarBruteForceMessage extends CaesarMessage {

    CaesarBruteForceMessage encryptedMessage;

    public CaesarBruteForceMessage(String targetSentence, int key, boolean topLevel) {
        super(targetSentence, key);
        if (topLevel) {
            encryptedMessage = new CaesarBruteForceMessage(getCorrectAnswer(), 0, false);
        }
        System.arraycopy(targetTextLetters, 0, selectedCipherLetters, 0, selectedCipherLetters.length);
    }

    public String encryptWithKey(int key) {
        return encryptedMessage.solveCipher(key);
    }
}
