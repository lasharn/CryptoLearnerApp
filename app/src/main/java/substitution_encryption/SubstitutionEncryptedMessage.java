package substitution_encryption;


import substitution_encryption.SubstitutionMappings;
import substitution_encryption.SubstitutionMessage;

public class SubstitutionEncryptedMessage extends SubstitutionMessage{

    public SubstitutionEncryptedMessage(String targetWord, SubstitutionMappings mappings) {
        super(targetWord, mappings);
    }

    @Override
    protected String calculateAnswer(String targetWord, SubstitutionMappings mappings) {
        StringBuilder answer = new StringBuilder();
        for (char c : targetWord.toCharArray()) {
            if (c == ' ' || (c>'Z' || c<'A')) {
                answer.append(Character.toString((char)c));
                continue;
            }
            for (int i = 'A'; i<='Z'; i++) {
                String letter = mappings.getLetter(Character.toString((char)i));
                if (letter.equals(Character.toString(c))) {
                    answer.append(Character.toString((char)i));
                    break;
                }
            }
        }

        return answer.toString();

    }
}
