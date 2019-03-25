//@author Баландин Артём
package ru.psu.studyit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import ru.psu.studyit.R;
import com.google.android.material.tabs.TabLayout;


public class LabTabs extends AppCompatActivity {

    ViewPager simpleViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_tabs);

        simpleViewPager = (ViewPager) findViewById(R.id.simpleViewPager);

        PagerAdapter adapter = new ru.psu.studyit.PagerAdapter(getSupportFragmentManager(),4);

        simpleViewPager.setAdapter(adapter);

        simpleViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }



    }



