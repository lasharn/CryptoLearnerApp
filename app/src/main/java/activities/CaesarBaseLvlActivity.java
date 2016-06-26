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

public abstract class CaesarBaseLvlActivity extends AppCompatActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    protected int challengeNo = 1;
    protected int layoutId = R.layout.activity_caesar_lvl;
    protected int instructionPart1 = R.string.caesar_lvl1_instr_part1;
    protected int instructionPart2 = R.string.caesar_lvl1_instr_part2;
    protected int targetLetterBackground = R.drawable.background_cipher_letter;
    protected int answerLetterBackground = R.drawable.background_plain_letter;

    private final ChallengeType challengeType = ChallengeType.CAESAR;
    private final int numberOfStages = 3;
    private int stage = 1;


    private TextView keyText;
    private CaesarMessage cipherMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupFields();

        setContentView(layoutId);
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

    abstract void setupFields();

    private void setupGame() {
        // display keyboard
        findViewById(R.id.KeyboardTable).setVisibility(View.VISIBLE);
        findViewById(R.id.SuccessMessage).setVisibility(View.GONE);

        // set key
        Random r = new Random();
        int key = r.nextInt(21) + 3; // purposely doesn't allow keys close to 0
        TextView task = (TextView) findViewById(R.id.InstructionText);

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
        cipherMessage = createCaesarMessage(targetWord, key);
        targetWord = cipherMessage.plainTextString();

        task.setText(getString(instructionPart1) + " " + targetWord + " " +
                getString(instructionPart2) + " " + key);

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


        // set answer string
        setupSolutionText(cipherMessage.cipherTextString());

        // set keyboard letters
        KeyboardLetterGenerator klg = new KeyboardLetterGenerator();
        setLetterButtons(klg.getKeyboardLetters(cipherMessage.getCorrectAnswer()));
    }

    abstract protected CaesarMessage createCaesarMessage(String targetWord, int key);


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
            cipherMessage.addLetter(btn.getText().toString());
            deactivateLetterBtn(btn);
        } else {
            cipherMessage.removeLetter(btn.getText().toString());
            activateLetterBtn(btn);
        }
        setupSolutionText(cipherMessage.cipherTextString());
        if (cipherMessage.isCorrect()) {
            stageComplete();
        }
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


    abstract public void onDialogContinueClick(DialogFragment dialog);

    private String getStageDisplayString() {
        return stage + "/" + numberOfStages;
    }

}
