package com.example.joshenglish.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.joshenglish.Fragments.Grammer;
import com.example.joshenglish.Fragments.Reading;
import com.example.joshenglish.Fragments.Speaking;
import com.example.joshenglish.Fragments.Vocabulary;

import java.util.Locale;

public class FragAdapter extends FragmentPagerAdapter {

    public FragAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return  new Vocabulary();
            case 1:
                return  new Speaking();
            case 2:
                return  new Grammer();
            case 3:
                return  new Reading();

            default: return new Speaking();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        String title = null;
        switch (position){
            case 0:
                title = "Vocabulary";
                break;
            case 1:
                title = "Speaking";
                break;
            case 2:
                title = "Grammer";
                break;
            case 3:
                title = "Reading";
                break;
            default: title = "Speaking";
        }
        return  title;

    }
}
