package general;


import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SentenceGenerator {
    private List<SentenceObject> sentences;
    private AssetManager assetManager;

    private SentenceObject currentSentence;

    public SentenceGenerator(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public String getSentence() {
        // random number
        Random random = new Random();
        int number = random.nextInt(getSentencesList().size());
        // use random number to get from list
        SentenceObject sentenceObject = getSentencesList().get(number);
        currentSentence = sentenceObject;
        // format word
        String sentence = formatSentence(sentenceObject.sentence);
        // return word
        return sentence;
    }

    public String getHint() {
        // random number
        Random random = new Random();
        int number = random.nextInt(currentSentence.hints.length);
        return currentSentence.hints[number];
    }

    private void readInSentences() {
        sentences = new ArrayList<>();
        try {
            JSONObject JSONContents = new JSONObject(loadJSONFromAsset());

            JSONArray sentencesJSON = JSONContents.getJSONArray("sentences");
            //System.out.println(sentencesJSON.toString());
            for (int i = 0; i < sentencesJSON.length(); i++) {
                JSONObject JSONSentence = sentencesJSON.getJSONObject(i);
                //System.out.println(JSONSentence.toString());
                JSONArray JSONHints = JSONSentence.getJSONArray("hints");
                List<String> hints = new ArrayList<>();
                for (int j = 0; j < JSONHints.length(); j++) {
                    String jj = JSONHints.getString(j);
                    hints.add(jj);
                }
                String[] theHints = (String[])hints.toArray(new String[hints.size()]);
                //String[] hints = JSONSentence.getJSONArray("hints").toString().replace("},{", " ,").split(" ");;
                sentences.add(new SentenceObject(JSONSentence.getString("sentence"), theHints));

            }

            //System.out.println(a.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<SentenceObject> getSentencesList() {
        if (sentences != null) {
            return sentences;
        } else {
            readInSentences();
            return sentences;
        }
    }

    private String formatSentence(String sentence) {
        return sentence.toUpperCase();
    }


    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = assetManager.open("sentences.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    private class SentenceObject {
        public String sentence;
        public String[] hints;

        public SentenceObject (String sentence, String[] hints) {
            this.sentence = sentence;
            this.hints = hints;
        }
    }
}


