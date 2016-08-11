package activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import java.util.LinkedHashSet;
import java.util.Set;

import caesar_encryption.CaesarMessage;
import general.KeyboardLetterGenerator;
import substitution_encryption.RandomMappingGenerator;
import substitution_encryption.SubstitutionMappings;
import substitution_encryption.SubstitutionPartiallyCompleteMessage;
import ui_elements.CaesarCompleteDialogFragment;
import general.ChallengeType;
import general.SentenceGenerator;

public class SubstitutionLvl3Activity extends BaseLvlActivity implements CaesarCompleteDialogFragment.Caesar1DialogListener {

    String encryptedSentence;
    String initialTargetLetters;

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


        setupInitialTargetLetters();
        setupSelectedText(cipherMessage.getSelectedString());
        highlightSelectedPosition();
    }


    private void setupSentences(String encrypted, String selected) {
        TextView encryptedText = (TextView)findViewById(R.id.substitution_encrypted_text);
        encryptedText.setText(encrypted);
        TextView selectedText = (TextView)findViewById(R.id.substitution_answer_text);
        selectedText.setText(selected);
    }

    private void setupInitialTargetLetters() {
        char[] messageChars = cipherMessage.getSelectedString().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<messageChars.length; i++) {
            char c = messageChars[i];
            if (c == CaesarMessage.emptyAnswerLetter.charAt(0)) {
                sb.append(cipherMessage.getCorrectAnswer().charAt(i));
            }
        }

        initialTargetLetters = sb.toString();
        initialTargetLetters = removeDuplicateLetters(initialTargetLetters);
        System.out.println(initialTargetLetters);
        setupTargetText(initialTargetLetters);

        //setupSelectedText(cipherMessage.getSelectedString());
    }


    private String removeDuplicateLetters(String string) {
        char[] chars = string.toCharArray();
        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }


    protected void setupTargetText(String word) {
        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.message_layout);
        messageLayout.removeAllViews();
        for (int i=0; i < word.length(); i++) {
            TextView letterView = new TextView(this);
            letterView.setText(word.charAt(i%word.length()) + "");
            letterView.setTextSize(20);
            letterView.setWidth((int)getResources().getDimension(R.dimen.letterWidth));
            letterView.setGravity(Gravity.CENTER);
            letterView.setBackgroundResource(R.drawable.background_key_letter);

            messageLayout.addView(letterView);
        }
    }

    private String getSelectedLetters() {
        char[] messageChars = cipherMessage.getCorrectAnswer().toCharArray();
        char[] idk = initialTargetLetters.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int j=0; j<idk.length; j++) {
            for (int i = 0; i < messageChars.length; i++) {
                if (idk[j] == messageChars[i]) {
                    sb.append(cipherMessage.getSelectedString().charAt(i));
                    break;
                }
            }
        }
        return sb.toString();
    }


    @Override
    protected void setupSelectedText(String text) {
        super.setupSelectedText(getSelectedLetters());
        setupSentences(encryptedSentence, cipherMessage.getSelectedString());
    }

    @Override
    protected void stageComplete() {
        super.stageComplete();
        setupSentences(encryptedSentence, cipherMessage.plainTextString());
    }

    @Override
    protected void letterBtnClicked(Button btn) {
        super.letterBtnClicked(btn);
        setupTargetText(initialTargetLetters);
        highlightSelectedPosition();
    }

    @Override
    protected void removeLetter(View v) {

        if (!((TextView)v).getText().equals(CaesarMessage.emptyAnswerLetter)) {
            super.removeLetter(v);
        } else {
            System.out.println("not really removing letter");
            int position = ((LinearLayout)findViewById(R.id.solution_layout)).indexOfChild(v);
            String letter = ((TextView)((LinearLayout)findViewById(R.id.message_layout)).getChildAt(position)).getText().toString();
            char[] messageChars = cipherMessage.getCorrectAnswer().toCharArray();
            char[] idk = initialTargetLetters.toCharArray();
                for (int i = 0; i < messageChars.length; i++) {
                    if (letter.charAt(0) == messageChars[i]) {
                        position = i;
                        break;
                    }
                }


            System.out.println(position + letter);
            ((SubstitutionPartiallyCompleteMessage)cipherMessage).setSelectedPosition(position);
            setupSelectedText(getSelectedLetters());
        }
        setupTargetText(initialTargetLetters);
        highlightSelectedPosition();
    }


    private void highlightSelectedPosition() {
        int position = ((SubstitutionPartiallyCompleteMessage)cipherMessage).getSelectedPosition();
        char c = cipherMessage.getCorrectAnswer().charAt(position);
        System.out.println(position + "" + c + "");
        int usablePosition = 0;
        for (int i=0; i<initialTargetLetters.length(); i++) {
            char ch = initialTargetLetters.charAt(i);
            if (ch == c) {
                usablePosition = i;
                break;
            }
        }

        ((LinearLayout)findViewById(R.id.message_layout)).getChildAt(usablePosition).setBackgroundResource(R.drawable.background_selected_cipher);
        ((LinearLayout)findViewById(R.id.solution_layout)).getChildAt(usablePosition).setBackgroundResource(R.drawable.background_selected_plain);
    }

}
