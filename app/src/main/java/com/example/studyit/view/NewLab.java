//@author Баландин
package com.example.studyit.view;

import android.R.layout;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyit.R;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewLab extends AppCompatActivity {

    String[] items_discipline = {"1 предмет", "2 предмет", "3 предмет", "4 предмет"};
    String[] items_controlPoints = {"1 КТ", "2 КТ", "3 КТ"}; //aepanov
    private TextInputEditText textViewLabName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        textViewLabName =  findViewById(R.id.textViewLabName1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lab);

        //aepanov start
        Spinner spinnerSubject = (Spinner) findViewById(R.id.spinnerDiscipline);
        spinnerSubject.setPrompt("Дисциплина");

        Spinner spinnerControlPoint = (Spinner) findViewById(R.id.spinnerCP);
        spinnerControlPoint.setPrompt("Контрольная точка");
        //aepanov end

        //TextView labName = (TextView) findViewById(R.id.textViewLabName);
        textViewLabName =  findViewById(R.id.textViewLabName1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items_discipline);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerSubject.setAdapter(adapter);

        //aepanov start
        ArrayAdapter<String> CP_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items_controlPoints);
        CP_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerControlPoint.setAdapter(CP_adapter);
        //aepanov end

        //maximzltrv start
        textViewLabName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int len = 0;
                if (textViewLabName.getText()!=null)
                    len = textViewLabName.getText().length();
                if(!hasFocus && len==0){

                    textViewLabName.setHint("Введите название лабораторной работы");
                }
                else
                {
                    textViewLabName.setHint("Название лабораторной работы");
                }
            }
        });
        //maximzltrv end

    }

    public void saveLab(View view)
    {
        //Скорее всего здесь будет код для сохранения лабораторной

        Toast toast = Toast.makeText(getApplicationContext(),
                "ДОБААААВЬ меня", Toast.LENGTH_SHORT);
        toast.show();



    }


}
