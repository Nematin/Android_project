package com.example.studyit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.example.studyit.Fragment_files;
import com.example.studyit.Fragment_main;
import com.example.studyit.Fragment_materials;
import com.example.studyit.Fragment_notes;
import com.example.studyit.R;
import com.google.android.material.tabs.TabLayout;


public class LabTabs extends AppCompatActivity {

    ViewPager simpleViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_tabs);

        simpleViewPager = (ViewPager) findViewById(R.id.simpleViewPager);

        PagerAdapter adapter = new com.example.studyit.PagerAdapter(getSupportFragmentManager(),4);

        simpleViewPager.setAdapter(adapter);

        simpleViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }



    }



