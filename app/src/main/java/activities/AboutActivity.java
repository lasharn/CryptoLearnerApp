package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.cryptolearner.mobile.cryptolearner.R;

import general.LevelUnlocks;

public class AboutActivity extends AppCompatActivity {

    AboutActivity _this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _this = this;

        findViewById(R.id.unlock_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelUnlocks.getInstance(_this).unlockAll();
                Toast.makeText(_this, "Levels Unlocked",
                        Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.reset_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelUnlocks.getInstance(_this).resetProgress();
                Toast.makeText(_this, "Progress Reset",
                        Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.do_nothing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_this, "Nothing Happened",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
