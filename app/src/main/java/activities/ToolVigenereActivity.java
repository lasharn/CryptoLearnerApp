package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import caesar_encryption.CaesarMessage;
import unpackaged.VigenereEncryptedMessage;
import unpackaged.VigenereMessage;

public class ToolVigenereActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_vigenere);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    public void encrypt(View view) {
        TextView tv = (EditText) findViewById(R.id.userEnteredMessage);
        String message = tv.getText().toString();

        TextView tv2 = (EditText) findViewById(R.id.userEnteredKeyword);
        String keyword = tv2.getText().toString();

        TextView tv3 = (EditText) findViewById(R.id.encryptedMessage);
        tv3.setText(new VigenereMessage(message, keyword).getCorrectAnswer());
    }

    public void decrypt(View view) {
        TextView tv = (EditText) findViewById(R.id.userEnteredMessage);
        String message = tv.getText().toString();

        TextView tv2 = (EditText) findViewById(R.id.userEnteredKeyword);
        String keyword = tv2.getText().toString();

        TextView tv3 = (EditText) findViewById(R.id.encryptedMessage);
        tv3.setText(new VigenereEncryptedMessage(message, keyword).getCorrectAnswer());
    }

}