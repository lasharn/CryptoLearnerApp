package com.cryptolearner.mobile.cryptolearner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import substitution_encryption.RandomMappingGenerator;
import substitution_encryption.SubstitutionMappings;
import substitution_encryption.SubstitutionMessage;
import unpackaged.IMessage;
import unpackaged.SnappyScrollView;
import unpackaged.SubstitutionEncryptedMessage;

public class ToolSubstitutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_substitution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup top scroller
        ArrayList<Integer> views = new ArrayList<>();
        views.add(R.layout.substitution_tool_part1);
        views.add(R.layout.substitution_tool_part2);
        ((SnappyScrollView)findViewById(R.id.substitution_scroller)).setFeatureItems(this, views);
    }

    public void encrypt(View view) {
        String[] letters = getMappings();
        SubstitutionMappings substitutionMappings = new SubstitutionMappings(letters);

        String targetMessage = ((TextView) findViewById(R.id.userEnteredMessage)).getText().toString();
        IMessage cipherMessage = new SubstitutionMessage(targetMessage, substitutionMappings);
        ((TextView) findViewById(R.id.encryptedMessage)).setText(cipherMessage.getCorrectAnswer());
    }

    public void decrypt(View view) {
        String[] letters = getMappings();
        SubstitutionMappings substitutionMappings = new SubstitutionMappings(letters);

        String targetMessage = ((TextView) findViewById(R.id.userEnteredMessage)).getText().toString();
        IMessage cipherMessage = new SubstitutionEncryptedMessage(targetMessage, substitutionMappings);
        ((TextView) findViewById(R.id.encryptedMessage)).setText(cipherMessage.getCorrectAnswer());
    }

    public void randomizeMappings(View view) {
        // setup random mappings
        String[] letters = new RandomMappingGenerator().getRandomMappings();
        SubstitutionMappings substitutionMappings = new SubstitutionMappings(letters);
        setupMappings(substitutionMappings.getLetterArray());
    }

    private String[] getMappings() {
        String[] letters = new String[26];
        TextView tv = (TextView) findViewById(R.id.substituteForA);
        letters[0] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForB);
        letters[1] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForC);
        letters[2] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForD);
        letters[3] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForE);
        letters[4] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForF);
        letters[5] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForG);
        letters[6] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForH);
        letters[7] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForI);
        letters[8] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForJ);
        letters[9] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForK);
        letters[10] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForL);
        letters[11] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForM);
        letters[12] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForN);
        letters[13] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForO);
        letters[14] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForP);
        letters[15] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForQ);
        letters[16] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForR);
        letters[17] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForS);
        letters[18] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForT);
        letters[19] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForU);
        letters[20] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForV);
        letters[21] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForW);
        letters[22] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForX);
        letters[23] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForY);
        letters[24] = tv.getText().toString();
        tv = (TextView) findViewById(R.id.substituteForZ);
        letters[25] = tv.getText().toString();

        return letters;
    }


    private void setupMappings(String[] letters) {
        TextView tv = (TextView) findViewById(R.id.substituteForA);
        tv.setText(letters[0]);
        tv = (TextView) findViewById(R.id.substituteForB);
        tv.setText(letters[1]);
        tv = (TextView) findViewById(R.id.substituteForC);
        tv.setText(letters[2]);
        tv = (TextView) findViewById(R.id.substituteForD);
        tv.setText(letters[3]);
        tv = (TextView) findViewById(R.id.substituteForE);
        tv.setText(letters[4]);
        tv = (TextView) findViewById(R.id.substituteForF);
        tv.setText(letters[5]);
        tv = (TextView) findViewById(R.id.substituteForG);
        tv.setText(letters[6]);
        tv = (TextView) findViewById(R.id.substituteForH);
        tv.setText(letters[7]);
        tv = (TextView) findViewById(R.id.substituteForI);
        tv.setText(letters[8]);
        tv = (TextView) findViewById(R.id.substituteForJ);
        tv.setText(letters[9]);
        tv = (TextView) findViewById(R.id.substituteForK);
        tv.setText(letters[10]);
        tv = (TextView) findViewById(R.id.substituteForL);
        tv.setText(letters[11]);
        tv = (TextView) findViewById(R.id.substituteForM);
        tv.setText(letters[12]);
        tv = (TextView) findViewById(R.id.substituteForN);
        tv.setText(letters[13]);
        tv = (TextView) findViewById(R.id.substituteForO);
        tv.setText(letters[14]);
        tv = (TextView) findViewById(R.id.substituteForP);
        tv.setText(letters[15]);
        tv = (TextView) findViewById(R.id.substituteForQ);
        tv.setText(letters[16]);
        tv = (TextView) findViewById(R.id.substituteForR);
        tv.setText(letters[17]);
        tv = (TextView) findViewById(R.id.substituteForS);
        tv.setText(letters[18]);
        tv = (TextView) findViewById(R.id.substituteForT);
        tv.setText(letters[19]);
        tv = (TextView) findViewById(R.id.substituteForU);
        tv.setText(letters[20]);
        tv = (TextView) findViewById(R.id.substituteForV);
        tv.setText(letters[21]);
        tv = (TextView) findViewById(R.id.substituteForW);
        tv.setText(letters[22]);
        tv = (TextView) findViewById(R.id.substituteForX);
        tv.setText(letters[23]);
        tv = (TextView) findViewById(R.id.substituteForY);
        tv.setText(letters[24]);
        tv = (TextView) findViewById(R.id.substituteForZ);
        tv.setText(letters[25]);


    }
}


