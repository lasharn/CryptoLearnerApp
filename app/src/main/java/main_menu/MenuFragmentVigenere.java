package main_menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cryptolearner.mobile.cryptolearner.R;

import unpackaged.ChallengeType;
import unpackaged.LevelUnlocks;


public class MenuFragmentVigenere extends Fragment {



    public MenuFragmentVigenere() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_vigenere, container, false);
        setupLvlUnlocks(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupLvlUnlocks(getView());
    }


    private void setupLvlUnlocks(View view) {
        LevelUnlocks levelUnlocks = LevelUnlocks.getInstance(getActivity());
        if (!levelUnlocks.isUnlocked(ChallengeType.VIGENERE, 1)) {
            lockLevel(view.findViewById(R.id.VigenereLvl1Btn));
        } else {
            unlockLevel(view.findViewById(R.id.VigenereLvl1Btn));
        }
        if (!levelUnlocks.isUnlocked(ChallengeType.VIGENERE, 2)) {
            lockLevel(view.findViewById(R.id.VigenereLvl2Btn));
        } else {
            unlockLevel(view.findViewById(R.id.VigenereLvl2Btn));
        }
        if (!levelUnlocks.isUnlocked(ChallengeType.VIGENERE, 3)) {
            lockLevel(view.findViewById(R.id.VigenereLvl3Btn));
        } else {
            unlockLevel(view.findViewById(R.id.VigenereLvl3Btn));
        }
        if (!levelUnlocks.isUnlocked(ChallengeType.VIGENERE, 4)) {
            lockLevel(view.findViewById(R.id.VigenereLvl4Btn));
        } else {
            unlockLevel(view.findViewById(R.id.VigenereLvl4Btn));
        }
    }

    private void lockLevel(View view) {
        ImageView iv = (ImageView) ((ViewGroup)view).getChildAt(1);
        iv.setImageDrawable(getActivity().getApplicationContext().getResources().getDrawable(R.drawable.icon_locked));
    }

    private void unlockLevel(View view) {
        ImageView iv = (ImageView) ((ViewGroup)view).getChildAt(1);
        iv.setImageDrawable(getActivity().getApplicationContext().getResources().getDrawable(R.drawable.icon_key));
    }

}
