//@author Баландин, Дегтяникова, Balyshev
package ru.psu.studyit.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import ru.psu.studyit.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import ru.psu.studyit.model.CLab;
import ru.psu.studyit.view.activities.lab.CActivityLab;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class CRecyclerViewAdapterLabs extends RecyclerView.Adapter<CRecyclerViewAdapterLabs.ViewHolder> {

    private LayoutInflater inflater;
    private List<CLab> labs;
    private Context context;

    public CRecyclerViewAdapterLabs(Context context, List<CLab> CLabs) {
        this.labs = CLabs;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public CRecyclerViewAdapterLabs.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_view_tasks_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CRecyclerViewAdapterLabs.ViewHolder holder, final int position) {
        CLab lab = labs.get(position);
        holder.nameView.setText(lab.getName());
        holder.disciplineView.setText(lab.getDiscipline());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, tasks.get(position).getName() + " is clicked!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: clicked on: " + labs.get(position));

                Toast.makeText(context, labs.get(position)+ " is clicked!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, CActivityLab.class);
                //intent.putExtra("image_url", tasks.get(position));
                //intent.putExtra("image_name", mImageNames.get(position));
                context.startActivity(intent);
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
        return labs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        final TextView disciplineView;
        final TextView nameView;
        final TextView pointView;
        final ImageView status;
        final LinearLayout linearLayout;
        ViewHolder(View view){
            super(view);
            linearLayout = view.findViewById(R.id.oneRow);
            nameView = view.findViewById(R.id.name);
            disciplineView = view.findViewById((R.id.discipline));
            pointView = view.findViewById((R.id.check_point));
            status = view.findViewById((R.id.status));
        }
    }
}
