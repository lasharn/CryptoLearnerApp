package main_menu;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;

import general.ChallengeType;
import general.LevelUnlocks;


public class MenuFragmentSubstitution extends Fragment {



    public MenuFragmentSubstitution() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_substitution, container, false);
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
        if (!levelUnlocks.isUnlocked(ChallengeType.SUBSTITUTION, 1)) {
            lockLevel(view.findViewById(R.id.SubstitutionLvl1Btn));
        } else {
            unlockLevel(view.findViewById(R.id.SubstitutionLvl1Btn));
        }
        if (!levelUnlocks.isUnlocked(ChallengeType.SUBSTITUTION, 2)) {
            lockLevel(view.findViewById(R.id.SubstitutionLvl2Btn));
        } else {
            unlockLevel(view.findViewById(R.id.SubstitutionLvl2Btn));
        }
        if (!levelUnlocks.isUnlocked(ChallengeType.SUBSTITUTION, 3)) {
            lockLevel(view.findViewById(R.id.SubstitutionLvl3Btn));
        } else {
            unlockLevel(view.findViewById(R.id.SubstitutionLvl3Btn));
        }
    }

    private void lockLevel(View view) {
        ImageView iv = (ImageView) ((ViewGroup)view).getChildAt(1);
        iv.setImageDrawable(getActivity().getApplicationContext().getResources().getDrawable(R.drawable.icon_locked));
        TextView tv = (TextView) ((ViewGroup)view).getChildAt(2);
        tv.setTextColor(Color.LTGRAY);
    }

    private void unlockLevel(View view) {
        ImageView iv = (ImageView) ((ViewGroup)view).getChildAt(1);
        iv.setImageDrawable(getActivity().getApplicationContext().getResources().getDrawable(R.drawable.icon_key));
        TextView tv = (TextView) ((ViewGroup)view).getChildAt(2);
        tv.setTextColor(Color.GRAY);
    }

}
