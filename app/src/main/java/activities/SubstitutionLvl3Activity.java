package activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import java.util.ArrayList;

import caesar_encryption.KeyboardLetterGenerator;
import substitution_encryption.RandomMappingGenerator;
import substitution_encryption.SubstitutionFrequencyMessage;
import substitution_encryption.SubstitutionMappings;
import substitution_encryption.SubstitutionMessage;
import substitution_encryption.SubstitutionPartiallyCompleteMessage;
import ui_elements.CaesarCompleteDialogFragment;
import unpackaged.ChallengeType;
import unpackaged.FrequencyCounter;
import unpackaged.KeyboardFrequencyLetterGenerator;
import unpackaged.SentenceGenerator;
import unpackaged.SnappyScrollView;

public class SubstitutionLvl3Activity extends BaseLvlActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    String encryptedSentence;

    public SubstitutionLvl3Activity() {
        challengeType = ChallengeType.SUBSTITUTION;
        challengeNo = 3;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;
        nextLevel = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution_lvl3);
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


        // setup random mappings
        String[] letters = new RandomMappingGenerator().getRandomMappings();

        SubstitutionMappings substitutionMappings = new SubstitutionMappings(letters);

        // Set sentence to solve
        SentenceGenerator sg = new SentenceGenerator(getAssets());
        String targetSentence = sg.getSentence();


        // setup answer text
        cipherMessage = new SubstitutionPartiallyCompleteMessage(targetSentence, substitutionMappings, 4);

        encryptedSentence = cipherMessage.getCorrectAnswer();

        // setup keyboard
        setLetterButtons(new KeyboardLetterGenerator().getKeyboardLetters(
                ((SubstitutionPartiallyCompleteMessage)cipherMessage).getKeyboardLetters()));

        //
        setupSentences(encryptedSentence, cipherMessage.getSelectedString());

    }


    private void setupSentences(String encrypted, String selected) {
        TextView encryptedText = (TextView)findViewById(R.id.substitution_encrypted_text);
        encryptedText.setText(encrypted);
        TextView selectedText = (TextView)findViewById(R.id.substitution_answer_text);
        selectedText.setText(selected);
    }


    @Override
    protected void setupSelectedText(String text) {
        super.setupSelectedText(text);
        setupSentences(encryptedSentence, cipherMessage.getSelectedString());
    }

    @Override
    protected void stageComplete() {
        super.stageComplete();
        setupSentences(encryptedSentence, cipherMessage.plainTextString());
    }


}
