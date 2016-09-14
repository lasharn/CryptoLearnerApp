package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import java.util.Arrays;

import caesar_encryption.CaesarToolMessage;

public class ToolCaesarDecryptionActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    String[] decryptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_caesar_decryption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        decryptions = new String[26];
        Arrays.fill(decryptions, "");
        adapter = new ArrayAdapter<>(this, R.layout.activity_caesar_listview, decryptions);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }


    public void decrypt(View view) {
        TextView tv = (EditText) findViewById(R.id.userEnteredMessage);
        String message = tv.getText().toString();
        CaesarToolMessage caesarMessage = new CaesarToolMessage(message, 0);

        for (int i=0; i<26; i++) {
            int j = 26-i;
            decryptions[i] = Character.toString((char)(i + 'A')) + ". " + caesarMessage.solveWithKey(j);
        }
        adapter.notifyDataSetChanged();
    }

}
