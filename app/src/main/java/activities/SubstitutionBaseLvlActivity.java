package activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import java.util.List;

import caesar_encryption.WordGenerator;
import ui_elements.CaesarCompleteDialogFragment;
import unpackaged.ChallengeType;
import unpackaged.LevelUnlocks;
import unpackaged.SubstitutionMappings;

public class SubstitutionBaseLvlActivity extends AppCompatActivity {

    private final ChallengeType challengeType = ChallengeType.SUBSTITUTION;
    private final int challengeNo = 1;
    private final int numberOfStages = 3;
    private int stage = 1;
    protected int targetLetterBackground = R.drawable.background_cipher_letter;
    protected int answerLetterBackground = R.drawable.background_plain_letter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution_lvl);
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
        //cipherMessage = createCaesarMessage(targetWord, key);
        //targetWord = cipherMessage.plainTextString();

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



        // setup mappings
        String[] letters = {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
        SubstitutionMappings substitutionMappings = new SubstitutionMappings(letters);
        setupMappings(substitutionMappings.getLetterArray());
    }


    private void setupSolutionText(String word) {
        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.solution_layout);
        messageLayout.removeAllViews();
        for (int i=0; i < word.length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText(word.charAt(i) + "");
            letterView.setTextSize(20);
            letterView.setWidth((int)getResources().getDimension(R.dimen.letterWidth));
            letterView.setGravity(Gravity.CENTER);
            letterView.setBackgroundResource(answerLetterBackground);

            messageLayout.addView(letterView);
        }
    }

    private void setKeyboardBtnListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Button b = (Button) v;
                letterBtnClicked(b);
            }
        };
        findViewById(R.id.LetterBtn1).setOnClickListener(listener);
        findViewById(R.id.LetterBtn2).setOnClickListener(listener);
        findViewById(R.id.LetterBtn3).setOnClickListener(listener);
        findViewById(R.id.LetterBtn4).setOnClickListener(listener);
        findViewById(R.id.LetterBtn5).setOnClickListener(listener);
        findViewById(R.id.LetterBtn6).setOnClickListener(listener);
        findViewById(R.id.LetterBtn7).setOnClickListener(listener);
        findViewById(R.id.LetterBtn8).setOnClickListener(listener);
    }

    private void letterBtnClicked(Button btn) {
        if (letterBtnIsActive(btn)) {
            //cipherMessage.addLetter(btn.getText().toString());
            deactivateLetterBtn(btn);
        } else {
            //cipherMessage.removeLetter(btn.getText().toString());
            activateLetterBtn(btn);
        }
        //setupSolutionText(cipherMessage.cipherTextString());
        //if (cipherMessage.isCorrect()) {
        //    stageComplete();
        //}
    }

    private void stageComplete() {
        stage++;
        if (stage > numberOfStages) {
            challengeComplete();
        } else {
            findViewById(R.id.KeyboardTable).setVisibility(View.GONE);
            findViewById(R.id.SuccessMessage).setVisibility(View.VISIBLE);
        }
    }

    private void challengeComplete() {
        LevelUnlocks levelUnlocks = LevelUnlocks.getInstance(this);
        levelUnlocks.levelComplete(challengeType, challengeNo);

        DialogFragment newFragment = new CaesarCompleteDialogFragment();
        newFragment.show(getSupportFragmentManager(), "congratulations");
    }


    private void deactivateLetterBtn(Button letterBtn) {
        letterBtn.setTextColor(Color.GRAY);
    }
    private void activateLetterBtn(Button letterBtn) {
        letterBtn.setTextColor(Color.BLACK);
    }
    private boolean letterBtnIsActive(Button letterBtn) {
        return letterBtn.getCurrentTextColor() != Color.GRAY;
    }

    private void setLetterButtons(List<String> letters) {

        int btnNo = R.id.LetterBtn1;
        Button btn;
        btn = (Button) findViewById(R.id.LetterBtn1);
        btn.setText(letters.get(0));
        activateLetterBtn(btn);
        btn = (Button) findViewById(R.id.LetterBtn2);
        btn.setText(letters.get(1));
        activateLetterBtn(btn);
        btn = (Button) findViewById(R.id.LetterBtn3);
        btn.setText(letters.get(2));
        activateLetterBtn(btn);
        btn = (Button) findViewById(R.id.LetterBtn4);
        btn.setText(letters.get(3));
        activateLetterBtn(btn);
        btn = (Button) findViewById(R.id.LetterBtn5);
        btn.setText(letters.get(4));
        activateLetterBtn(btn);
        btn = (Button) findViewById(R.id.LetterBtn6);
        btn.setText(letters.get(5));
        activateLetterBtn(btn);
        btn = (Button) findViewById(R.id.LetterBtn7);
        btn.setText(letters.get(6));
        activateLetterBtn(btn);
        btn = (Button) findViewById(R.id.LetterBtn8);
        btn.setText(letters.get(7));
        activateLetterBtn(btn);
    }


    public void onDialogContinueClick(DialogFragment dialog) {
        //Intent intent = new Intent(this, CaesarLvl2Activity.class);
        //startActivity(intent);
        finish();
    }

    private String getStageDisplayString() {
        return stage + "/" + numberOfStages;
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
