package activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import java.util.ArrayList;

import substitution_encryption.RandomMappingGenerator;
import substitution_encryption.SubstitutionFrequencyMessage;
import substitution_encryption.SubstitutionMappings;
import substitution_encryption.SubstitutionMessage;
import ui_elements.CaesarCompleteDialogFragment;
import general.ChallengeType;
import general.FrequencyCounter;
import general.KeyboardFrequencyLetterGenerator;
import general.SentenceGenerator;
import ui_elements.SnappyScrollView;

public class SubstitutionLvl2Activity extends BaseLvlActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    String encryptedSentence;

    public SubstitutionLvl2Activity() {
        challengeType = ChallengeType.SUBSTITUTION;
        challengeNo = 2;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;
        nextLevel = SubstitutionLvl3Activity.class;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution_lvl2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Integer> views = new ArrayList<>();
        views.add(R.layout.substitution_sentence_1);
        views.add(R.layout.substitution_sentence_2);
        ((SnappyScrollView)findViewById(R.id.substitution_scroller)).setFeatureItems(this, views);

        setupGame();

        setKeyboardBtnListeners();

        findViewById(R.id.CompleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupGame();
                TextView stageDisplay = (TextView) findViewById(R.id.stageDisplay);
                stageDisplay.setText(getStageDisplayString());
            }
        });


    }


    private void setupGame() {

        // display keyboard
        findViewById(R.id.KeyboardTable).setVisibility(View.VISIBLE);
        findViewById(R.id.SuccessMessage).setVisibility(View.GONE);


        // set letters to solve
        String targetWord = "ETA";

        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.message_layout);
        messageLayout.removeAllViews();
        for (int i=0; i < targetWord.length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText(targetWord.charAt(i) + "");
            letterView.setTextSize(20);
            letterView.setWidth((int)getResources().getDimension(R.dimen.letterWidth));
            letterView.setGravity(Gravity.CENTER);
            letterView.setBackgroundResource(targetLetterBackground);

            messageLayout.addView(letterView);
        }

        // setup random mappings
        String[] letters = new RandomMappingGenerator().getRandomMappings();

        SubstitutionMappings substitutionMappings = new SubstitutionMappings(letters);

        // Set sentence to solve
        SentenceGenerator sg = new SentenceGenerator(getAssets());
        String targetSentence = sg.getSentence();
        encryptedSentence = new SubstitutionMessage(targetSentence, substitutionMappings).getCorrectAnswer();

        // setup hint text
        ((TextView)findViewById(R.id.hint_word)).setText(getString(R.string.substitution_lvl2_hint1)
                + sg.getHint() + getString(R.string.substitution_lvl2_hint2));


        setupFrequencies(new FrequencyCounter().getCounts(encryptedSentence));


        // setup answer text
        cipherMessage = new SubstitutionFrequencyMessage(targetSentence, substitutionMappings);

        // setup keyboard
        setLetterButtons(new KeyboardFrequencyLetterGenerator().getKeyboardLetters(new FrequencyCounter().getCounts(encryptedSentence)));

        //
        setupSentences(encryptedSentence, ((SubstitutionFrequencyMessage)cipherMessage).selectedSentence());

        //
        setupSelectedText(cipherMessage.getSelectedString());
    }


    private void setupSentences(String encrypted, String selected) {
        TextView encryptedText = (TextView)findViewById(R.id.substitution_encrypted_text);
        encryptedText.setText(encrypted);
        TextView selectedText = (TextView)findViewById(R.id.substitution_answer_text);
        selectedText.setText(selected);
    }


    @Override
    protected void setupSelectedText(String text) {
        super.setupSelectedText(text);
        setupSentences(encryptedSentence, ((SubstitutionFrequencyMessage)cipherMessage).selectedSentence());
    }

    @Override
    protected void stageComplete() {
        super.stageComplete();
        setupSentences(encryptedSentence, ((SubstitutionFrequencyMessage)cipherMessage).plainTextSentence());
    }


    private void setupFrequencies(int[] frequencies) {
        int position = getMostFrequentLetter(frequencies);
        TextView tv = (TextView) findViewById(R.id.CommonLetter1);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency1);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;

        position = getMostFrequentLetter(frequencies);
        tv = (TextView) findViewById(R.id.CommonLetter2);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency2);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;

        position = getMostFrequentLetter(frequencies);
        tv = (TextView) findViewById(R.id.CommonLetter3);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency3);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;

        position = getMostFrequentLetter(frequencies);
        tv = (TextView) findViewById(R.id.CommonLetter4);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency4);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;

        position = getMostFrequentLetter(frequencies);
        tv = (TextView) findViewById(R.id.CommonLetter5);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency5);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;

        position = getMostFrequentLetter(frequencies);
        tv = (TextView) findViewById(R.id.CommonLetter6);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency6);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;

        position = getMostFrequentLetter(frequencies);
        tv = (TextView) findViewById(R.id.CommonLetter7);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency7);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;

        position = getMostFrequentLetter(frequencies);
        tv = (TextView) findViewById(R.id.CommonLetter8);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency8);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;

        position = getMostFrequentLetter(frequencies);
        tv = (TextView) findViewById(R.id.CommonLetter9);
        tv.setText(Character.toString((char)(position + 'A')));
        tv = (TextView) findViewById(R.id.LetterFrequency9);
        tv.setText(frequencies[position] + "");
        frequencies[position] = -1;
    }


    private int getMostFrequentLetter(int[] frequencies) {
        int max = frequencies[0];
        int positionOfMax = 0;

        for (int i = 1; i < frequencies.length; i++) {
            if (frequencies[i] > max) {
                max = frequencies[i];
                positionOfMax = i;
            }
        }
        return positionOfMax;
    }

}
