package unpackaged;


import java.util.HashMap;

public class SubstitutionMappings {
    HashMap<String, String> mappings = new HashMap<>();

    public SubstitutionMappings(String[] letters) {
        assignMappings(letters);
    }

    private void assignMappings(String[] letters) {
        for (int i='A'; i<='Z'; i++) {
            mappings.put(Character.toString((char)i), letters[i-'A']);
        }
    }

    public String getLetter(String letter) {
        return mappings.get(letter);
    }
    public String[] getLetterArray() {
        String[] letters = new String[26];
        for (int i=0; i<26; i++) {
            letters[i] = mappings.get(Character.toString((char)(i + 'A')));
        }
        return letters;
    }
}
