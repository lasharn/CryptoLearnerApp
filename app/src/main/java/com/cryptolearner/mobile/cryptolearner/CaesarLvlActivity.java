package com.cryptolearner.mobile.cryptolearner;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CaesarLvlActivity extends AppCompatActivity {

    private TextView keyText;
    private TextView answer;
    private CaesarMessage cipherMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_lvl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Random r = new Random();
        int key = r.nextInt(23) + 3;
        TextView task = (TextView) findViewById(R.id.InstructionText);
        task.setText(task.getText()  + " " + key);

        TextView message = (TextView) findViewById(R.id.TargetText);
        cipherMessage = new CaesarMessage(message.getText().toString(), key);

        keyText = (TextView) findViewById(R.id.KeyText);
        CipherWheelView cipherWheelView = (CipherWheelView) findViewById(R.id.cipher_wheel);
        assert cipherWheelView != null;
        cipherWheelView.addDialListener(new CipherWheelView.DialListener() {
            public void onDial(int number) {
                keyText.setText(String.format(Locale.ENGLISH, "Key:\n%d", number));
            }
        });

        answer = (TextView) findViewById(R.id.AnswerText);
        setBtnListener();

        // TODO initialize game (instructions, target message, letter buttons)
        KeyboardLetterGenerator klg = new KeyboardLetterGenerator();
        setLetterButtons(klg.getKeyboardLetters(cipherMessage.getCorrectAnswer()));
        //answer.setText(klg.getKeyboardLetters(cipherMessage.getCorrectAnswer()).toString());
        //message.setText(cipherMessage.getCorrectAnswer());
    }

    private void setBtnListener() {
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
        answer.setText(cipherMessage.cipherTextString());
        if (cipherMessage.isCorrect()) {
            TextView message = (TextView) findViewById(R.id.TargetText);
            message.setText("Yay you encoded the message");
        }
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
        btn = (Button) findViewById(R.id.LetterBtn2);
        btn.setText(letters.get(1));
        btn = (Button) findViewById(R.id.LetterBtn3);
        btn.setText(letters.get(2));
        btn = (Button) findViewById(R.id.LetterBtn4);
        btn.setText(letters.get(3));
        btn = (Button) findViewById(R.id.LetterBtn5);
        btn.setText(letters.get(4));
        btn = (Button) findViewById(R.id.LetterBtn6);
        btn.setText(letters.get(5));
        btn = (Button) findViewById(R.id.LetterBtn7);
        btn.setText(letters.get(6));
        btn = (Button) findViewById(R.id.LetterBtn8);
        btn.setText(letters.get(7));
    }


}
