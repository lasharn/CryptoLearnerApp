package unpackaged;


public class FrequencyCounter {

    public int[] getCounts(String sentence) {
        int[] counts = new int[26];
        for (char c : sentence.toCharArray()) {
            // space should be ignored
            if (c != ' ') {
                counts[c-'A'] = counts[c-'A'] + 1;
            }
        }

        return counts;
    }
}
