package vigenere_encryption;


import vigenere_encryption.VigenereMessage;

public class VigenereKeywordMessage extends VigenereMessage {


    public VigenereKeywordMessage(String targetWord, String keyword) {
        super(targetWord, keyword);
    }

    @Override
    public boolean isCorrect() {
        return getSelectedString().substring(0,getKeyword().length()).equals(getKeyword());
    }

}
