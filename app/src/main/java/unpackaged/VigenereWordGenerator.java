package unpackaged;


import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VigenereWordGenerator {

    private List<String> words;
    private List<String> keywords;
    private AssetManager assetManager;

    public VigenereWordGenerator(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public String getWord() {
        // random number
        Random random = new Random();
        int number = random.nextInt(getWordsList().size());
        // use random number to get from list
        String word = getWordsList().get(number);
        // format word
        word = formatWord(word);
        // return word
        return word;
    }

    public String getKey() {
        // random number
        Random random = new Random();
        int number = random.nextInt(getKeysList().size());
        // use random number to get from list
        String keyword = getKeysList().get(number);
        // format word
        keyword = formatWord(keyword);
        // return word
        return keyword;
    }

    private void readInWords() {
        try {
            InputStream inputStream = assetManager.open("words.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> lines = new ArrayList<>();
            List<String> keys = new ArrayList<>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() <= 3) {
                    keys.add(line);
                } else {
                    lines.add(line);
                }
            }
            words = lines;
            keywords = keys;
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getWordsList() {
        if (words != null) {
            return words;
        } else {
            readInWords();
            return words;
        }
    }

    private List<String> getKeysList() {
        if (keywords != null) {
            return keywords;
        } else {
            readInWords();
            return keywords;
        }
    }

    private String formatWord(String word) {
        return word.toUpperCase();
    }

}
