package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cryptolearner.mobile.cryptolearner.R;

import unpackaged.ChallengeType;
import unpackaged.LevelUnlocks;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupLvlUnlocks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset_progress) {
            LevelUnlocks.getInstance(this).resetProgress();
            setupLvlUnlocks();
            return true;
        }
        if (id == R.id.unlock_all) {
            LevelUnlocks.getInstance(this).unlockAll();
            setupLvlUnlocks();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (pd != null) {
            pd.dismiss();
        }
        setupLvlUnlocks();
    }

    public void onClickCaesar(View view) {
        findViewById(R.id.Background).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mainColour));
        View caesar1 = findViewById(R.id.CaesarLvl1Btn);
        if (caesar1.getVisibility() == View.VISIBLE) {
            caesarGone(true);
        } else {
            caesarGone(false);
        }
    }

    public void onClickSubstitution(View view) {
        findViewById(R.id.Background).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colourMix));
        if (findViewById(R.id.SubLvl1Btn).getVisibility() == View.VISIBLE) {
            subGone(true);
        } else {
            subGone(false);
        }
    }

    public void onClickVigenere(View view) {
        findViewById(R.id.Background).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.secondaryColour));
        if (findViewById(R.id.VigenereLvl1Btn).getVisibility() == View.VISIBLE) {
            vigenereGone(true);
        } else {
            vigenereGone(false);
        }
    }

    public void onClickChallenge(View view) {
        boolean levelLocked = false;
        Intent intent = new Intent(this, CaesarLvlActivity.class);
        switch (view.getId()) {
            case R.id.CaesarLvl1Btn:
                intent = new Intent(this, CaesarLvlActivity.class);
                break;
            case R.id.CaesarLvl2Btn:
                if (isLevelUnlocked(ChallengeType.CAESAR, 2)) {
                    intent = new Intent(this, CaesarLvlActivity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.CaesarLvl3Btn:
                if (isLevelUnlocked(ChallengeType.CAESAR, 3)) {
                    intent = new Intent(this, CaesarLvlActivity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.CaesarLvl4Btn:
                if (isLevelUnlocked(ChallengeType.CAESAR, 4)) {
                    intent = new Intent(this, CaesarLvlActivity.class);
                } else {
                    levelLocked = true;
                }
                break;
        }

        if (levelLocked) {
            Toast.makeText(this, "Level not unlocked",
                    Toast.LENGTH_SHORT).show();
        } else {
            pd = ProgressDialog.show(this, "Loading",
                    "Please wait...");

            startActivity(intent);
        }

    }


    private void caesarGone(boolean setGone) {

        View caesar1 = findViewById(R.id.CaesarLvl1Btn);
        View caesar2 = findViewById(R.id.CaesarLvl2Btn);
        View caesar3 = findViewById(R.id.CaesarLvl3Btn);
        View caesar4 = findViewById(R.id.CaesarLvl4Btn);
        if (setGone) {
            caesar1.setVisibility(View.GONE);
            caesar2.setVisibility(View.GONE);
            caesar3.setVisibility(View.GONE);
            caesar4.setVisibility(View.GONE);
        } else {
            caesar1.setVisibility(View.VISIBLE);
            caesar2.setVisibility(View.VISIBLE);
            caesar3.setVisibility(View.VISIBLE);
            caesar4.setVisibility(View.VISIBLE);
            subGone(true);
            vigenereGone(true);
        }
    }

    private void subGone(boolean setGone) {
        View btn1 = findViewById(R.id.SubLvl1Btn);
        View btn2 = findViewById(R.id.SubLvl2Btn);
        View btn3 = findViewById(R.id.SubLvl3Btn);
        if (setGone) {
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
        } else {
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            caesarGone(true);
            vigenereGone(true);
        }
    }

    private void vigenereGone(boolean setGone) {
        View btn1 = findViewById(R.id.VigenereLvl1Btn);
        View btn2 = findViewById(R.id.VigenereLvl2Btn);
        View btn3 = findViewById(R.id.VigenereLvl3Btn);
        if (setGone) {
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
        } else {
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            caesarGone(true);
            subGone(true);
        }
    }

    private boolean isLevelUnlocked(ChallengeType type, int challengeNo) {
        LevelUnlocks levelUnlocks = LevelUnlocks.getInstance(this);
        return levelUnlocks.isUnlocked(type, challengeNo);
    }

    private void setupLvlUnlocks() {
        LevelUnlocks levelUnlocks = LevelUnlocks.getInstance(this);
        if (!levelUnlocks.isUnlocked(ChallengeType.CAESAR, 2)) {
            lockLevel(findViewById(R.id.CaesarLvl2Btn));
        } else {
            unlockLevel(findViewById(R.id.CaesarLvl2Btn));
        }
        if (!levelUnlocks.isUnlocked(ChallengeType.CAESAR, 3)) {
            lockLevel(findViewById(R.id.CaesarLvl3Btn));
        } else {
            unlockLevel(findViewById(R.id.CaesarLvl3Btn));
        }
        if (!levelUnlocks.isUnlocked(ChallengeType.CAESAR, 4)) {
            lockLevel(findViewById(R.id.CaesarLvl4Btn));
        } else {
            unlockLevel(findViewById(R.id.CaesarLvl4Btn));
        }
    }

    private void lockLevel(View view) {
        ImageView iv = (ImageView) ((ViewGroup)view).getChildAt(1);
        iv.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_locked));
    }

    private void unlockLevel(View view) {
        ImageView iv = (ImageView) ((ViewGroup)view).getChildAt(1);
        iv.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.icon_key));
    }
}
