package activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ui_elements.CaesarCompleteDialogFragment;
import caesar_encryption.CaesarMessage;
import ui_elements.CipherWheelView;
import caesar_encryption.KeyboardLetterGenerator;
import com.cryptolearner.mobile.cryptolearner.R;
import caesar_encryption.WordGenerator;

import java.util.Locale;
import java.util.Random;

public abstract class CaesarBaseLvlActivity extends BaseLvlActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    protected int layoutId = R.layout.activity_caesar_lvl;
    protected int instructionPart1 = R.string.caesar_lvl1_instr_part1;
    protected int instructionPart2 = R.string.caesar_lvl1_instr_part2;

    private TextView keyText;

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

        TextView task = (TextView) findViewById(R.id.InstructionText);
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
        setupSelectedText(cipherMessage.getSelectedString());

        // set keyboard letters
        KeyboardLetterGenerator klg = new KeyboardLetterGenerator();
        setLetterButtons(klg.getKeyboardLetters(cipherMessage.getCorrectAnswer()));
    }

    abstract protected CaesarMessage createCaesarMessage(String targetWord, int key);


    abstract public void onDialogContinueClick(DialogFragment dialog);


}
