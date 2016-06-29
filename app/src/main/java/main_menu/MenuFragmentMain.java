package main_menu;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cryptolearner.mobile.cryptolearner.R;

import activities.CaesarLvl1Activity;
import activities.CaesarLvl2Activity;
import activities.MainActivity;
import unpackaged.ChallengeType;


public class MenuFragmentMain extends Fragment {

    private ProgressDialog pd;

    public MenuFragmentMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_main, container, false);
    }


}
