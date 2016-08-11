package caesar_encryption;


import caesar_encryption.CaesarMessage;

public class CaesarToolMessage extends CaesarMessage {

    public CaesarToolMessage(String targetWord, int key) {
        super(targetWord,key);
    }

    public String solveWithKey(int key) {
        return solveCipher(key);
    }
}
