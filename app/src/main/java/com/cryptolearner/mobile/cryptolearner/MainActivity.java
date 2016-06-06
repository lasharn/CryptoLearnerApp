package com.cryptolearner.mobile.cryptolearner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        pd = ProgressDialog.show(this, "Loading",
                "Please wait");
        Intent intent = new Intent(this, CaesarLvlActivity.class);
        startActivity(intent);
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


}
