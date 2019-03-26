//@author Дегтяникова Дарья
package ru.psu.studyit.view.activities.lab;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ru.psu.studyit.R;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Context context;
    public PagerAdapter(
            Context context,
            FragmentManager fm,
            int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;

        this.context                        = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CFragmentLabDetails tab1 = new CFragmentLabDetails();
                return tab1;

            case 1:
                Fragment_materials tab2 = new Fragment_materials();
                return tab2;
            case 2:
                CFragmentLabFiles tab3 = new CFragmentLabFiles();
                return tab3;
            case 3:
                Fragment_notes tab4 = new Fragment_notes();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return "Page " + position;

        switch (position) {
            case 0:
                return context.getString(R.string.Details);
            case 1:
                return context.getString(R.string.Materials);
            case 2:
                return context.getString(R.string.Files);
            case 3:
                return context.getString(R.string.Comments);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
