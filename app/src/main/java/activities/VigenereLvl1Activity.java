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

import caesar_encryption.CaesarMessage;
import general.KeyboardLetterGenerator;
import ui_elements.CipherWheelView;
import general.ChallengeType;
import vigenere_encryption.VigenereMessage;
import vigenere_encryption.VigenereWordGenerator;

public class VigenereLvl1Activity extends BaseLvlActivity {

    private TextView keyText;

    public VigenereLvl1Activity() {
        challengeType = ChallengeType.VIGENERE;
        challengeNo = 1;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;
        nextLevel = VigenereLvl2Activity.class;
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
        helpMessage = getResources().getString(R.string.help_vigenere_lvl_1);

        // display keyboard
        findViewById(R.id.KeyboardTable).setVisibility(View.VISIBLE);
        findViewById(R.id.SuccessMessage).setVisibility(View.GONE);


        // set letters to solve
        VigenereWordGenerator vwg = new VigenereWordGenerator(getAssets());
        String targetWord = vwg.getWord();
        String keyword = vwg.getKey();

        // setup instructions
        ((TextView)findViewById(R.id.InstructionText)).setText(getString(R.string.vigenere_lvl1_instr_part1) +
            targetWord + getString(R.string.vigenere_lvl1_instr_part2) + " \"" + keyword + "\"");

        // setup target word
        setupTargetText(targetWord);

        // update key when cipher wheel spins
        keyText = (TextView) findViewById(R.id.KeyText);
        CipherWheelView cipherWheelView = (CipherWheelView) findViewById(R.id.cipher_wheel);
        keyText.setText("Key:\n" + ((char)(cipherWheelView.getKey()+'A')));
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

        highlightSelectedPosition();
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
            letterView.setOnClickListener(selectedTextListener);

            messageLayout.addView(letterView);
        }
    }

    protected void setupTargetText(String targetWord) {
        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.message_layout);
        LinearLayout keywordLayout = (LinearLayout) findViewById(R.id.keyword_layout);
        if (targetWord == null) {
            for (int i=0; i < cipherMessage.getCorrectAnswer().length(); i++) {
                messageLayout.getChildAt(i).setBackgroundResource(R.drawable.background_plain_letter);
                keywordLayout.getChildAt(i).setBackgroundResource(R.drawable.background_key_letter);
            }
            return;
        }

        messageLayout.removeAllViews();
        for (int i=0; i < targetWord.length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText(targetWord.charAt(i) + "");
            letterView.setTextSize(20);
            letterView.setWidth((int)getResources().getDimension(R.dimen.letterWidth));
            letterView.setGravity(Gravity.CENTER);
            letterView.setBackgroundResource(targetLetterBackground);
            letterView.setOnClickListener(selectedTextListener);

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
        setupTargetText(null);
        highlightSelectedPosition();

    }

    @Override
    protected void removeLetter(View v) {
        //setup position
        int position = ((LinearLayout)v.getParent()).indexOfChild(v);
        TextView selectedLetter = ((TextView) ((LinearLayout)findViewById(R.id.solution_layout)).getChildAt(position));
        ((VigenereMessage)cipherMessage).setSelectedPosition(position);
        if (!selectedLetter.getText().equals(CaesarMessage.emptyAnswerLetter)) {
            super.removeLetter(selectedLetter);

        } else {
            setupSelectedText(cipherMessage.getSelectedString());
        }

        setupTargetText(null);
        highlightSelectedPosition();


    }

    private void highlightSelectedPosition() {
        int position = ((VigenereMessage)cipherMessage).getSelectedPosition();
        ((LinearLayout)findViewById(R.id.message_layout)).getChildAt(position).setBackgroundResource(R.drawable.background_selected_cipher);
        ((LinearLayout)findViewById(R.id.keyword_layout)).getChildAt(position).setBackgroundResource(R.drawable.background_selected_key);
        ((LinearLayout)findViewById(R.id.solution_layout)).getChildAt(position).setBackgroundResource(R.drawable.background_selected_plain);
    }
}
