package com.example.studyit.view;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.studyit.LabTabs;
import com.example.studyit.R;
import com.example.studyit.model.CTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    List<CTask> tasks = new ArrayList<CTask>();

    @Override
    public boolean shouldUpRecreateTask(Intent targetIntent) {
        return super.shouldUpRecreateTask(targetIntent);
    }

    public void onFABClick(View view)
    {
        Intent intent = new Intent(this, NewLab.class);
        startActivity(intent);
    }


    public void onFABQRClick(View view)
    {
        Intent intent = new Intent(this, qrCode.class);
        startActivity(intent);
    }

    public void onRecListClick(View view)
        {
            Intent intent = new Intent(this, LabTabs.class);
            startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks.add(new CTask("Lab1", "11", "1", "1",UUID.randomUUID()));
        tasks.add(new CTask("Lab2", "12", "2", "2",UUID.randomUUID()));
        tasks.add(new CTask("Lab3", "13", "3", "3",UUID.randomUUID()));
        tasks.add(new CTask("Lab4", "14", "4", "4",UUID.randomUUID()));

        RecyclerView rv = (RecyclerView)findViewById(R.id.RecyclerViewTasks);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        ViewAdaptor adapter = new ViewAdaptor(this, tasks);
        // устанавливаем для списка адаптер
        rv.setAdapter(adapter);


}
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, LabTabs.class);
        startActivity(intent);
        }

}
