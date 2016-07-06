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

public class SubstitutionLvl1Activity extends BaseLvlActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {


    public SubstitutionLvl1Activity() {
        challengeType = ChallengeType.SUBSTITUTION;
        challengeNo = 1;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution_lvl1);
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
    }


    private void setupGame() {
        // display keyboard
        findViewById(R.id.KeyboardTable).setVisibility(View.VISIBLE);
        findViewById(R.id.SuccessMessage).setVisibility(View.GONE);

        // Set word to solve
        WordGenerator generator = new WordGenerator(getApplicationContext().getAssets());
        String targetWord = generator.getWord();

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
        setupMappings(substitutionMappings.getLetterArray());

        // setup answer text
        cipherMessage = new SubstitutionMessage(targetWord, substitutionMappings);

        // setup keyboard
        setLetterButtons(new KeyboardLetterGenerator().getKeyboardLetters(cipherMessage.getCorrectAnswer()));

        //
        setupSelectedText(cipherMessage.getSelectedString());
    }


    private void setupMappings(String[] letters) {
        TextView tv = (TextView) findViewById(R.id.substituteForA);
        tv.setText(letters[0]);
        tv = (TextView) findViewById(R.id.substituteForB);
        tv.setText(letters[1]);
        tv = (TextView) findViewById(R.id.substituteForC);
        tv.setText(letters[2]);
        tv = (TextView) findViewById(R.id.substituteForD);
        tv.setText(letters[3]);
        tv = (TextView) findViewById(R.id.substituteForE);
        tv.setText(letters[4]);
        tv = (TextView) findViewById(R.id.substituteForF);
        tv.setText(letters[5]);
        tv = (TextView) findViewById(R.id.substituteForG);
        tv.setText(letters[6]);
        tv = (TextView) findViewById(R.id.substituteForH);
        tv.setText(letters[7]);
        tv = (TextView) findViewById(R.id.substituteForI);
        tv.setText(letters[8]);
        tv = (TextView) findViewById(R.id.substituteForJ);
        tv.setText(letters[9]);
        tv = (TextView) findViewById(R.id.substituteForK);
        tv.setText(letters[10]);
        tv = (TextView) findViewById(R.id.substituteForL);
        tv.setText(letters[11]);
        tv = (TextView) findViewById(R.id.substituteForM);
        tv.setText(letters[12]);
        tv = (TextView) findViewById(R.id.substituteForN);
        tv.setText(letters[13]);
        tv = (TextView) findViewById(R.id.substituteForO);
        tv.setText(letters[14]);
        tv = (TextView) findViewById(R.id.substituteForP);
        tv.setText(letters[15]);
        tv = (TextView) findViewById(R.id.substituteForQ);
        tv.setText(letters[16]);
        tv = (TextView) findViewById(R.id.substituteForR);
        tv.setText(letters[17]);
        tv = (TextView) findViewById(R.id.substituteForS);
        tv.setText(letters[18]);
        tv = (TextView) findViewById(R.id.substituteForT);
        tv.setText(letters[19]);
        tv = (TextView) findViewById(R.id.substituteForU);
        tv.setText(letters[20]);
        tv = (TextView) findViewById(R.id.substituteForV);
        tv.setText(letters[21]);
        tv = (TextView) findViewById(R.id.substituteForW);
        tv.setText(letters[22]);
        tv = (TextView) findViewById(R.id.substituteForX);
        tv.setText(letters[23]);
        tv = (TextView) findViewById(R.id.substituteForY);
        tv.setText(letters[24]);
        tv = (TextView) findViewById(R.id.substituteForZ);
        tv.setText(letters[25]);


    }

}
