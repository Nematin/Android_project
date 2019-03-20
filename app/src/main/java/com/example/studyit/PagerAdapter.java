//@author Дегтяникова Дарья
package com.example.studyit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Fragment_main tab1 = new Fragment_main();
                return tab1;
            case 1:
                Fragment_files tab2 = new Fragment_files();
                return tab2;
            case 2:
                Fragment_materials tab3 = new Fragment_materials();
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

                return "Главная";
            case 1:
                return "Файлы";
            case 2:
                return "Материалы";
            case 3:
                return "Чат";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
