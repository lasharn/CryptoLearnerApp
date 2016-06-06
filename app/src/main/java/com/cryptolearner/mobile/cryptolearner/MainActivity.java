package com.cryptolearner.mobile.cryptolearner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private SharedPreferences prefs;

    private static final String cryptoPreferences = "CryptoPrefs";
    private static final String lvlUnlockKey = "lvlUnlockKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = this.getSharedPreferences("com.cryptolearner.mobile.cryptolearner", Context.MODE_PRIVATE);
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
        if (id == R.id.action_settings) {
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
        findViewById(R.id.Background).setBackgroundColor(Color.parseColor("#FA6900"));
        View caesar1 = findViewById(R.id.CaesarLvl1Btn);
        if (caesar1.getVisibility() == View.VISIBLE) {
            caesarGone(true);
        } else {
            caesarGone(false);
        }
    }

    public void onClickSubstitution(View view) {
        findViewById(R.id.Background).setBackgroundColor(Color.parseColor("#E0E4CC"));
        if (findViewById(R.id.SubLvl1Btn).getVisibility() == View.VISIBLE) {
            subGone(true);
        } else {
            subGone(false);
        }
    }

    public void onClickVigenere(View view) {
        findViewById(R.id.Background).setBackgroundColor(Color.parseColor("#69D2E7"));
        if (findViewById(R.id.VigenereLvl1Btn).getVisibility() == View.VISIBLE) {
            vigenereGone(true);
        } else {
            vigenereGone(false);
        }
    }

    public void onClickChallenge(View view) {
        if (view.getId() == R.id.CaesarLvl1Btn) {
            pd = ProgressDialog.show(this, "Loading",
                    "Please wait");
            Intent intent = new Intent(this, CaesarLvlActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Level not implemented", // change this to not unlocked
                    Toast.LENGTH_SHORT).show();
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


    private void setupLvlUnlocks() {
        int currentUnlocks = prefs.getInt(lvlUnlockKey, 1);
        if (currentUnlocks < 2) {
            lockLevel(findViewById(R.id.CaesarLvl2Btn));
        }
        if (currentUnlocks < 3) {
            lockLevel(findViewById(R.id.CaesarLvl3Btn));
        }
        if (currentUnlocks < 4) {
            lockLevel(findViewById(R.id.CaesarLvl4Btn));
        }
    }

    private void lockLevel(View view) {
        ImageView iv = (ImageView) ((ViewGroup)view).getChildAt(1);
        iv.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.locked_icon));
    }
}
