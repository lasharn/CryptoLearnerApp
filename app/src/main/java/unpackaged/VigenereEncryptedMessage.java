package unpackaged;


public class VigenereEncryptedMessage extends VigenereMessage {

    public VigenereEncryptedMessage(String targetWord, String keyword) {
        super(targetWord, keyword);
    }

    @Override
    protected String calculateAnswer(String targetWord, String keyword) {
        StringBuilder answer = new StringBuilder();
        char[] targetLetters = targetWord.toCharArray();
        for (int i = 0; i<targetLetters.length; i++) {
            if (targetLetters[i] == ' ' || (targetLetters[i]>'Z' || targetLetters[i]<'A')) {
                answer.append(targetLetters[i]);
                continue;
            }
            int key = 26 - (keyword.toCharArray()[i%keyword.length()] - 'A');
            char c = (char) (targetLetters[i] + key);
            if (c > 'Z') {
                c -= 26;
            }
            answer.append(c);
        }

        return answer.toString();
    }
}
