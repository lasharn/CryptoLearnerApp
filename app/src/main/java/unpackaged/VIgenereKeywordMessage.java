package unpackaged;


public class VigenereKeywordMessage extends VigenereMessage {


    public VigenereKeywordMessage(String targetWord, String keyword) {
        super(targetWord, keyword);
    }

    @Override
    public boolean isCorrect() {
        return getSelectedString().substring(0,getKeyword().length()).equals(getKeyword());
    }

}
