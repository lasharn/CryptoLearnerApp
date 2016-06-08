package caesar_encryption;


import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordGenerator {

    private List<String> words;
    private AssetManager assetManager;

    public WordGenerator(AssetManager assetManager) {
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

    private void readInWords() {
        //AssetManager am = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            words = lines;
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

    private String formatWord(String word) {
        return word.toUpperCase();
    }

}
