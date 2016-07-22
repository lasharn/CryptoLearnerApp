package main_menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import activities.AboutActivity;
import com.cryptolearner.mobile.cryptolearner.R;
import com.cryptolearner.mobile.cryptolearner.ToolSubstitutionActivity;

import activities.ToolCaesarDecryptionActivity;

import activities.CaesarLvl1Activity;
import activities.CaesarLvl2Activity;
import activities.CaesarLvl3Activity;
import activities.CaesarLvl4Activity;
import activities.SubstitutionLvl1Activity;
import activities.SubstitutionLvl2Activity;
import activities.SubstitutionLvl3Activity;
import activities.ToolCaesarEncryptionActivity;
import activities.VigenereLvl1Activity;
import activities.VigenereLvl2Activity;
import unpackaged.ChallengeType;
import unpackaged.LevelUnlocks;

public class MenuActivity extends AppCompatActivity {

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MenuPagerAdapter menuPagerAdapter =
                new MenuPagerAdapter(
                        getSupportFragmentManager());
        PagerAdapter infiniteWrapper = new InfinitePagerAdapter(menuPagerAdapter);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(infiniteWrapper);
        viewPager.setCurrentItem(viewPager.getChildCount() * 20, false); // set current item in the adapter to middle
    }


    @Override
    public void onResume() {
        super.onResume();
        if (pd != null) {
            pd.dismiss();
        }
        //setupLvlUnlocks();
    }


    public void onClickChallenge(View view) {
        boolean levelLocked = false;
        Intent intent = new Intent(this, CaesarLvl1Activity.class);
        switch (view.getId()) {
            case R.id.CaesarLvl1Btn:
                intent = new Intent(this, CaesarLvl1Activity.class);
                break;
            case R.id.CaesarLvl2Btn:
                if (isLevelUnlocked(ChallengeType.CAESAR, 2)) {
                    intent = new Intent(this, CaesarLvl2Activity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.CaesarLvl3Btn:
                if (isLevelUnlocked(ChallengeType.CAESAR, 3)) {
                    intent = new Intent(this, CaesarLvl3Activity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.CaesarLvl4Btn:
                if (isLevelUnlocked(ChallengeType.CAESAR, 4)) {
                    intent = new Intent(this, CaesarLvl4Activity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.SubstitutionLvl1Btn:
                if (isLevelUnlocked(ChallengeType.SUBSTITUTION, 1)) {
                    intent = new Intent(this, SubstitutionLvl1Activity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.SubstitutionLvl2Btn:
                if (isLevelUnlocked(ChallengeType.SUBSTITUTION, 2)) {
                    intent = new Intent(this, SubstitutionLvl2Activity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.SubstitutionLvl3Btn:
                if (isLevelUnlocked(ChallengeType.SUBSTITUTION, 3)) {
                    intent = new Intent(this, SubstitutionLvl3Activity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.VigenereLvl1Btn:
                if (isLevelUnlocked(ChallengeType.VIGENERE, 1)) {
                    intent = new Intent(this, VigenereLvl1Activity.class);
                } else {
                    levelLocked = true;
                }
                break;
            case R.id.VigenereLvl2Btn:
                if (isLevelUnlocked(ChallengeType.VIGENERE, 2)) {
                    intent = new Intent(this, VigenereLvl2Activity.class);
                } else {
                    levelLocked = true;
                }
                break;

            case R.id.tools_btn1:
                intent = new Intent(this, ToolCaesarEncryptionActivity.class);
                break;
            case R.id.tools_btn2:
                intent = new Intent(this, ToolCaesarDecryptionActivity.class);
                break;
            case R.id.tools_btn3:
                intent = new Intent(this, ToolSubstitutionActivity.class);
                break;
            case R.id.tools_btn4:
                levelLocked = true;
                // intent = new Intent(this, ToolCaesarEncryptionActivity.class);
                break;

            case R.id.about_btn:
                intent = new Intent(this, AboutActivity.class);
                break;
            case R.id.something_else_btn:
                levelLocked = true;
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

    private boolean isLevelUnlocked(ChallengeType type, int challengeNo) {
        LevelUnlocks levelUnlocks = LevelUnlocks.getInstance(this);
        return levelUnlocks.isUnlocked(type, challengeNo);
    }



}
