//@author Баландин, Дегтяникова
package com.example.studyit.view;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.studyit.LabTabs;
import com.example.studyit.R;
import com.example.studyit.model.*;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ViewAdaptor extends RecyclerView.Adapter<ViewAdaptor.ViewHolder> {

    private LayoutInflater inflater;
    private List<CTask> tasks;
    private Context context;

    ViewAdaptor(Context context, List<CTask> CTasks) {
        this.tasks = CTasks;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public ViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_view_tasks_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewAdaptor.ViewHolder holder, final int position) {
        CTask CTask = tasks.get(position);
        holder.nameView.setText(CTask.getName());
        holder.disciplineView.setText(CTask.getDiscipline());
        //holder.status.setImageDrawable();

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, tasks.get(position).getName() + " is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void on(View view)
//    {
//        Intent intent = new Intent(this, .class);
//        startActivity(intent);
//    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView disciplineView;
        final TextView nameView;
        final TextView pointView;
        final ImageView status;
        final LinearLayout linearLayout;
        ViewHolder(View view){
            super(view);
            linearLayout = (LinearLayout) view.findViewById(R.id.oneRow);
            nameView = (TextView) view.findViewById(R.id.name);
            disciplineView = (TextView) view.findViewById((R.id.discipline));
            pointView = (TextView) view.findViewById((R.id.check_point));
            status = (ImageView) view.findViewById((R.id.status));
        }
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
