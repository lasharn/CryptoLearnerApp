package activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ui_elements.CaesarCompleteDialogFragment;
import caesar_encryption.CaesarMessage;
import ui_elements.CipherWheelView;
import caesar_encryption.KeyboardLetterGenerator;
import com.cryptolearner.mobile.cryptolearner.R;
import caesar_encryption.WordGenerator;
import unpackaged.ChallengeType;
import unpackaged.LevelUnlocks;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CaesarLvlActivity extends AppCompatActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    private final int challengeNo = 1;
    private final ChallengeType challengeType = ChallengeType.CAESAR;
    private int stage = 1;

    private TextView keyText;
//    private TextView answer;
    private CaesarMessage cipherMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_lvl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupGame();

        setKeyboardBtnListeners();

        findViewById(R.id.CompleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupGame();
            }
        });
    }

    private void setupGame() {
        // display keyboard
        findViewById(R.id.KeyboardTable).setVisibility(View.VISIBLE);
        findViewById(R.id.SuccessMessage).setVisibility(View.GONE);

        // set key
        Random r = new Random();
        int key = r.nextInt(21) + 3; // doesn't allow keys close to 0
        TextView task = (TextView) findViewById(R.id.InstructionText);
        task.setText(getString(R.string.caesar_lvl1_instr)  + " " + key);

        keyText = (TextView) findViewById(R.id.KeyText);
        CipherWheelView cipherWheelView = (CipherWheelView) findViewById(R.id.cipher_wheel);
        assert cipherWheelView != null;
        cipherWheelView.addDialListener(new CipherWheelView.DialListener() {
            public void onDial(int number) {
                keyText.setText(String.format(Locale.ENGLISH, "Key:\n%d", number));
            }
        });

        // Set word to solve
        WordGenerator generator = new WordGenerator(getApplicationContext().getAssets());
        String targetWord = generator.getWord();
//        TextView message = (TextView) findViewById(R.id.TargetText);
//        message.setText(targetWord);
        cipherMessage = new CaesarMessage(targetWord, key);


        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.message_layout);
        //findViewById(R.id.TargetText).setVisibility(View.GONE);
        messageLayout.removeAllViews();
        for (int i=0; i < targetWord.length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText(targetWord.charAt(i) + "");
            letterView.setTextSize(20);
            letterView.setWidth(50);
            letterView.setGravity(Gravity.CENTER);
            letterView.setBackgroundResource(R.drawable.letter_background);

            messageLayout.addView(letterView);
        }


        // set answer string
//        answer = (TextView) findViewById(R.id.AnswerText);
//        answer.setText(cipherMessage.cipherTextString());

        setupSolutionText(cipherMessage.cipherTextString());
        //findViewById(R.id.AnswerText).setVisibility(View.GONE);

        // set keyboard letters
        KeyboardLetterGenerator klg = new KeyboardLetterGenerator();
        setLetterButtons(klg.getKeyboardLetters(cipherMessage.getCorrectAnswer()));
    }

    private void setupSolutionText(String word) {
        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.solution_layout);
        messageLayout.removeAllViews();
        //findViewById(R.id.AnswerText).setVisibility(View.GONE);
        for (int i=0; i < word.length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText(word.charAt(i) + "");
            letterView.setTextSize(20);
            letterView.setWidth(50);
            letterView.setGravity(Gravity.CENTER);
            letterView.setBackgroundResource(R.drawable.letter_background);

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
            cipherMessage.addLetter(btn.getText().toString());
            deactivateLetterBtn(btn);
        } else {
            cipherMessage.removeLetter(btn.getText().toString());
            activateLetterBtn(btn);
        }
        //answer.setText(cipherMessage.cipherTextString());
        setupSolutionText(cipherMessage.cipherTextString());
        if (cipherMessage.isCorrect()) {
            stageComplete();
        }
    }

    private void stageComplete() {
        stage++;
        if (stage > 3) {
            challengeComplete();
        } else {
            findViewById(R.id.KeyboardTable).setVisibility(View.GONE);
            findViewById(R.id.SuccessMessage).setVisibility(View.VISIBLE);
        }
    }

    private void challengeComplete() {
        LevelUnlocks levelUnlocks = LevelUnlocks.getInstance();
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
        finish();
    }

}