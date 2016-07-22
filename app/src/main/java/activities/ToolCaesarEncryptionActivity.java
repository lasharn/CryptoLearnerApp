package activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import caesar_encryption.CaesarMessage;

public class ToolCaesarEncryptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_caesar_encryption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NumberPicker np = (NumberPicker) findViewById(R.id.keyPicker);
        np.setMaxValue(25);
        np.setMinValue(0);
        np.setValue(0);

    }


    public void encrypt(View view) {
        NumberPicker np = (NumberPicker) findViewById(R.id.keyPicker);
        int key = np.getValue();
        TextView tv = (EditText) findViewById(R.id.userEnteredMessage);
        String message = tv.getText().toString();
        TextView tv2 = (EditText) findViewById(R.id.encryptedMessage);
        tv2.setText(new CaesarMessage(message, key).getCorrectAnswer());
    }

}
