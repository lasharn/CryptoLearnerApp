package activities;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import java.util.Locale;

import caesar_encryption.KeyboardLetterGenerator;
import ui_elements.CipherWheelView;
import unpackaged.ChallengeType;
import unpackaged.VigenereMessage;
import unpackaged.VigenereWordGenerator;

public class VigenereLvl1Activity extends BaseLvlActivity {

    private TextView keyText;

    public VigenereLvl1Activity() {
        challengeType = ChallengeType.VIGENERE;
        challengeNo = 1;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere_lvl1);
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


        // set letters to solve TODO change to actual word
        VigenereWordGenerator vwg = new VigenereWordGenerator(getAssets());
        String targetWord = vwg.getWord();
        String keyword = vwg.getKey();

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

        // update key when cipher wheel spins
        keyText = (TextView) findViewById(R.id.KeyText);
        keyText.setText("Key:\nA");
        CipherWheelView cipherWheelView = (CipherWheelView) findViewById(R.id.cipher_wheel);
        assert cipherWheelView != null;
        cipherWheelView.addDialListener(new CipherWheelView.DialListener() {
            public void onDial(int number) {
                keyText.setText(String.format(Locale.ENGLISH, "Key:\n%c", number+'A'));
            }
        });

        //
        cipherMessage = new VigenereMessage(targetWord, keyword);

        // setup keyboard
        setLetterButtons(new KeyboardLetterGenerator().getKeyboardLetters(cipherMessage.getCorrectAnswer()));


        setupSelectedText(cipherMessage.getSelectedString());

        setupKeywordText(keyword);

        //((TextView)findViewById(R.id.Keyword)).setText(cipherMessage.getCorrectAnswer() + " " + keyword);
    }



    protected void setupKeywordText(String word) {
        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.keyword_layout);
        messageLayout.removeAllViews();
        for (int i=0; i < cipherMessage.getCorrectAnswer().length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText(word.charAt(i%word.length()) + "");
            letterView.setTextSize(20);
            letterView.setWidth((int)getResources().getDimension(R.dimen.letterWidth));
            letterView.setGravity(Gravity.CENTER);
            letterView.setBackgroundResource(R.drawable.background_key_letter);

            messageLayout.addView(letterView);
        }
    }

    @Override
    protected void letterBtnClicked(Button btn) {
        if (cipherMessage.isFull()) {
            return;
        }
        cipherMessage.addLetter(btn.getText().toString());

        setupSelectedText(cipherMessage.getSelectedString());
        if (cipherMessage.isCorrect()) {
            stageComplete();
        }
    }
}
