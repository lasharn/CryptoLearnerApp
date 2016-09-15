package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cryptolearner.mobile.cryptolearner.R;

import vigenere_encryption.VigenereEncryptedMessage;
import vigenere_encryption.VigenereMessage;

public class ToolVigenereActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_vigenere);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ((EditText)findViewById(R.id.userEnteredKeyword)).setFilters(new InputFilter[] {
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if(src.equals("")){ // for backspace
                            return src;
                        }
                        if(src.toString().matches("[A-Z]+")){
                            return src;
                        }
                        return "";
                    }
                }
        });

    }


    public void encrypt(View view) {
        if (keywordIsNull()) {
            return;
        }
        TextView tv = (EditText) findViewById(R.id.userEnteredMessage);
        String message = tv.getText().toString();

        TextView tv2 = (EditText) findViewById(R.id.userEnteredKeyword);
        String keyword = tv2.getText().toString();

        TextView tv3 = (EditText) findViewById(R.id.encryptedMessage);
        tv3.setText(new VigenereMessage(message, keyword).getCorrectAnswer());
    }

    public void decrypt(View view) {
        if (keywordIsNull()) {
            return;
        }
        TextView tv = (EditText) findViewById(R.id.userEnteredMessage);
        String message = tv.getText().toString();

        TextView tv2 = (EditText) findViewById(R.id.userEnteredKeyword);
        String keyword = tv2.getText().toString();

        TextView tv3 = (EditText) findViewById(R.id.encryptedMessage);
        tv3.setText(new VigenereEncryptedMessage(message, keyword).getCorrectAnswer());
    }

    private boolean keywordIsNull() {
        Editable keyword = ((EditText) findViewById(R.id.userEnteredKeyword)).getText();
        if (keyword.toString().equals("")) {
            Toast.makeText(this, "Please enter the keyword first",
                    Toast.LENGTH_SHORT).show();
        }
        return (keyword.toString().equals(""));
    }

}
