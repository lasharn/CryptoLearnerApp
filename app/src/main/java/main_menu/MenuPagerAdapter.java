package main_menu;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MenuPagerAdapter extends FragmentPagerAdapter {

    public MenuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MenuFragmentMain();
        } else if (position == 1) {
            return new MenuFragmentCaesar();
        } else if (position == 2) {
            return new MenuFragmentSubstitution();
        } else if (position == 3) {
            return new MenuFragmentVigenere();
        } else if (position == 4) {
            return new MenuFragmentTools();
        }
        return new MenuFragmentCaesar();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
