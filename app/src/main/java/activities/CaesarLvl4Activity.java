package activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import java.util.Locale;
import java.util.Random;

import caesar_encryption.CaesarGivenLetterMessage;
import caesar_encryption.CaesarMessage;
import caesar_encryption.WordGenerator;
import ui_elements.CaesarCompleteDialogFragment;
import ui_elements.CipherWheelView;
import unpackaged.CaesarBruteForceMessage;
import unpackaged.ChallengeType;
import unpackaged.IMessage;
import unpackaged.LevelUnlocks;
import unpackaged.SentenceGenerator;

/**
 * The activity for level 4 of the caesar challenges
 * In this level players must decrypt a sentence with brute force
 */
public class CaesarLvl4Activity extends AppCompatActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    private final int numberOfStages = 3;
    protected int stage = 1;
    protected int answerLetterBackground;
    protected int targetLetterBackground;
    protected IMessage cipherMessage;
    protected ChallengeType challengeType;
    protected int challengeNo;
    protected int layoutId = R.layout.activity_caesar_lvl4;
    protected Class nextLevel;

    private TextView keyText;
    private TextView PlainText;
    private int key;

    public CaesarLvl4Activity() {
        challengeType = ChallengeType.CAESAR;
        challengeNo = 4;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layoutId);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupGame();


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
        findViewById(R.id.Sentences).setVisibility(View.VISIBLE);
        findViewById(R.id.SuccessMessage).setVisibility(View.GONE);

        // set key
        Random r = new Random();
        key = r.nextInt(21) + 3; // purposely doesn't allow keys close to 0

        keyText = (TextView) findViewById(R.id.KeyText);
        CipherWheelView cipherWheelView = (CipherWheelView) findViewById(R.id.cipher_wheel);
        assert cipherWheelView != null;
        cipherWheelView.addDialListener(new CipherWheelView.DialListener() {
            public void onDial(int number) {
                keyText.setText(String.format(Locale.ENGLISH, "Key:\n%d", number));
                PlainText.setText(((CaesarBruteForceMessage)cipherMessage).encryptWithKey(number));
            }
        });

        // Set sentence to solve
        SentenceGenerator generator = new SentenceGenerator(getApplicationContext().getAssets());
        String targetWord = generator.getSentence();
        cipherMessage = new CaesarBruteForceMessage(targetWord, key, true);

        ((TextView) findViewById(R.id.EncryptedText)).setText(cipherMessage.getCorrectAnswer());
        PlainText = (TextView) findViewById(R.id.PlainText);
        PlainText.setText(((CaesarBruteForceMessage)cipherMessage).encryptWithKey(0));

        // set instruction text
        TextView task = (TextView) findViewById(R.id.InstructionText);
        task.setText("Work out the answer");


        ((Button) findViewById(R.id.SomeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cipherMessage.plainTextString().equals(cipherMessage.getSelectedString())) {
                    stageComplete();
                }
            }
        });
    }


    protected void stageComplete() {
        stage++;
        if (stage > numberOfStages) {
            challengeComplete();
        } else {
            findViewById(R.id.Sentences).setVisibility(View.GONE);
            findViewById(R.id.SuccessMessage).setVisibility(View.VISIBLE);
        }
    }

    private void challengeComplete() {
        LevelUnlocks levelUnlocks = LevelUnlocks.getInstance(this);
        levelUnlocks.levelComplete(challengeType, challengeNo);

        DialogFragment newFragment = new CaesarCompleteDialogFragment();
        newFragment.show(getSupportFragmentManager(), "congratulations");
    }

    public void onDialogContinueClick(DialogFragment dialog) {
        if (nextLevel != null) {
            Intent intent = new Intent(this, CaesarLvl2Activity.class);
            startActivity(intent);
        }
        finish();
    }

    protected String getStageDisplayString() {
        return stage + "/" + numberOfStages;
    }

}