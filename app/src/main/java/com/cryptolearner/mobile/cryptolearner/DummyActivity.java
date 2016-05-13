package com.cryptolearner.mobile.cryptolearner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DummyActivity extends AppCompatActivity {

    private TextView digits;
    private TextView answer;
    private CaesarMessage cipherMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView message = (TextView) findViewById(R.id.textView);
        cipherMessage = new CaesarMessage(message.getText().toString(), 7);

        digits = (TextView) findViewById(R.id.textView4);
        RotaryDialerView rotaryDialerView = (RotaryDialerView) findViewById(R.id.rotary_dialer);
        assert rotaryDialerView != null;
        rotaryDialerView.addDialListener(new RotaryDialerView.DialListener() {
            public void onDial(int number) {
                digits.setText("Key:\n" + String.valueOf(number));
            }
        });

        answer = (TextView) findViewById(R.id.textView2);
        setBtnListener();
    }

    private void setBtnListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                TextView b = (TextView) v;
                doSomethingWithClick(b);
            }
        };
        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);
        final Button button5 = (Button) findViewById(R.id.button5);
        final Button button6 = (Button) findViewById(R.id.button6);
        final Button button7 = (Button) findViewById(R.id.button7);
        final Button button8 = (Button) findViewById(R.id.button8);


        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
    }

    private void doSomethingWithClick(TextView btn) {
        setAnswerText(btn.getText().toString());
        //answer.setText(btn.getText());
        //enableBtns();
        btn.setEnabled(false);
        if (cipherMessage.isCorrect()) {
            TextView message = (TextView) findViewById(R.id.textView);
            message.setText("Yay you encoded the message");
        }
    }

    private void enableBtns() {
        findViewById(R.id.button1).setEnabled(true);
        findViewById(R.id.button2).setEnabled(true);
        findViewById(R.id.button3).setEnabled(true);
        findViewById(R.id.button4).setEnabled(true);
        findViewById(R.id.button5).setEnabled(true);
        findViewById(R.id.button6).setEnabled(true);
        findViewById(R.id.button7).setEnabled(true);
        findViewById(R.id.button8).setEnabled(true);
    }

    private void setAnswerText(String btnLetter) {
        cipherMessage.addLetter(btnLetter);
        answer.setText(cipherMessage.cipherTextString());
    }
}
