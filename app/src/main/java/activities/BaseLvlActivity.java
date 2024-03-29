package activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import java.util.List;

import ui_elements.CaesarCompleteDialogFragment;
import general.ChallengeType;
import general.IMessage;
import general.LevelUnlocks;
import ui_elements.HelpDialogFragment;


public abstract class BaseLvlActivity extends AppCompatActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    private final int numberOfStages = 3;
    protected int stage = 1;
    protected int answerLetterBackground;
    protected int targetLetterBackground;
    protected IMessage cipherMessage;
    protected ChallengeType challengeType;
    protected int challengeNo;
    protected Class nextLevel;
    protected String helpMessage;

    protected SelectedTextListener selectedTextListener = new SelectedTextListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    protected void setupSelectedText(String word) {
        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.solution_layout);
        messageLayout.removeAllViews();
        for (int i=0; i < word.length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText(word.charAt(i) + "");
            letterView.setTextSize(20);
            letterView.setWidth((int)getResources().getDimension(R.dimen.letterWidth));
            letterView.setGravity(Gravity.CENTER);
            letterView.setBackgroundResource(answerLetterBackground);
            letterView.setOnClickListener(selectedTextListener);

            messageLayout.addView(letterView);
        }
    }

    public class SelectedTextListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            removeLetter(v);
        }
    }

    protected void removeLetter(View v) {
        cipherMessage.removeLetter(((TextView)v).getText().toString());
        Button b;
        if ((b = getButtonOfLetter(((TextView)v).getText().toString())) != null) {
            activateLetterBtn(b);
        }
        setupSelectedText(cipherMessage.getSelectedString());
    }



    protected void setKeyboardBtnListeners() {
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

    protected void letterBtnClicked(Button btn) {
        if (letterBtnIsActive(btn)) {
            if (cipherMessage.isFull()) {
                return;
            }
            cipherMessage.addLetter(btn.getText().toString());
            deactivateLetterBtn(btn);
        } else {
            cipherMessage.removeLetter(btn.getText().toString());
            activateLetterBtn(btn);
        }
        setupSelectedText(cipherMessage.getSelectedString());
        if (cipherMessage.isCorrect()) {
            stageComplete();
        }
    }

    protected void stageComplete() {
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

    protected void setLetterButtons(List<String> letters) {

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
        if (nextLevel != null) {
            Intent intent = new Intent(this, nextLevel);
            startActivity(intent);
        }
        finish();
    }

    public void help(View view) {
        DialogFragment newFragment = HelpDialogFragment.newInstance(helpMessage);
        newFragment.show(getSupportFragmentManager(), "help");
    }

    protected String getStageDisplayString() {
        return stage + "/" + numberOfStages;
    }


    private Button getButtonOfLetter(String letter) {
        Button btn;
        btn = (Button) findViewById(R.id.LetterBtn1);
        if (btn.getText().equals(letter)) {
            return btn;
        }
        btn = (Button) findViewById(R.id.LetterBtn2);
        if (btn.getText().equals(letter)) {
            return btn;
        }
        btn = (Button) findViewById(R.id.LetterBtn3);
        if (btn.getText().equals(letter)) {
            return btn;
        }
        btn = (Button) findViewById(R.id.LetterBtn4);
        if (btn.getText().equals(letter)) {
            return btn;
        }
        btn = (Button) findViewById(R.id.LetterBtn5);
        if (btn.getText().equals(letter)) {
            return btn;
        }
        btn = (Button) findViewById(R.id.LetterBtn6);
        if (btn.getText().equals(letter)) {
            return btn;
        }
        btn = (Button) findViewById(R.id.LetterBtn7);
        if (btn.getText().equals(letter)) {
            return btn;
        }
        btn = (Button) findViewById(R.id.LetterBtn8);
        if (btn.getText().equals(letter)) {
            return btn;
        }
        return null;
    }


}
