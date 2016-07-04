package activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import caesar_encryption.KeyboardLetterGenerator;
import caesar_encryption.WordGenerator;
import substitution_encryption.RandomMappingGenerator;
import substitution_encryption.SubstitutionMappings;
import substitution_encryption.SubstitutionMessage;
import ui_elements.CaesarCompleteDialogFragment;
import unpackaged.ChallengeType;
import unpackaged.FrequencyCounter;

public class SubstitutionLvl2Activity extends BaseLvlActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    private boolean frequenciesSetup = false;
    String encryptedSentence;

    public SubstitutionLvl2Activity() {
        challengeType = ChallengeType.SUBSTITUTION;
        challengeNo = 2;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;
    }

    private boolean isSentenceDisplayed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution_lvl2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        findViewById(R.id.swipe_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSentenceDisplayed) {
                    findViewById(R.id.sentences).setVisibility(View.GONE);
                    findViewById(R.id.frequency).setVisibility(View.VISIBLE);
                    isSentenceDisplayed = false;
                } else {
                    findViewById(R.id.sentences).setVisibility(View.VISIBLE);
                    findViewById(R.id.frequency).setVisibility(View.GONE);
                    isSentenceDisplayed = true;
                }

                if (frequenciesSetup == false) {
                    setupFrequencies(new FrequencyCounter().getCounts(encryptedSentence));
                    frequenciesSetup = true;
                }
            }
        });
    }


    private void setupGame() {
        frequenciesSetup = false;

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
        String targetSentence = "THE MARTIANS ARE COMING TO GET US";
        encryptedSentence = new SubstitutionMessage(targetSentence, substitutionMappings).getCorrectAnswer();
        // TODO replace something with actual thing
        setupSentences(encryptedSentence, "something");
        

        // setup answer text
        cipherMessage = new SubstitutionMessage(targetWord, substitutionMappings);

        // setup keyboard
        setLetterButtons(new KeyboardLetterGenerator().getKeyboardLetters(cipherMessage.getCorrectAnswer()));

        //
        setupSolutionText(cipherMessage.getSelectedString());
    }


    private void setupSentences(String encrypted, String selected) {
        TextView encryptedText = (TextView)findViewById(R.id.substitution_encrypted_text);
        encryptedText.setText(encrypted);
        TextView selectedText = (TextView)findViewById(R.id.substitution_answer_text);
        selectedText.setText(selected);
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
        //frequencies[positionOfMax] = -1;
        return positionOfMax;
    }

}
