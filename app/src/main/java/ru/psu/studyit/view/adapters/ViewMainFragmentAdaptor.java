//@author Баландин, Дегтяникова
package ru.psu.studyit.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.psu.studyit.R;
import ru.psu.studyit.model.CLab;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ViewMainFragmentAdaptor extends RecyclerView.Adapter<CRecyclerViewAdapterLabs.ViewHolder> {

private LayoutInflater inflater;
private List<CLab> tasks;
private Context context;

        ViewMainFragmentAdaptor(Context context, List<CLab> CLabs) {
        this.tasks = CLabs;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        }
    @Override
    public CRecyclerViewAdapterLabs.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.fragment_main, parent, false);
        return new CRecyclerViewAdapterLabs.ViewHolder(view);
    }

    @Override
public void onBindViewHolder(CRecyclerViewAdapterLabs.ViewHolder holder, final int position) {
        CLab CLab = tasks.get(position);
        holder.nameView.setText(CLab.getName());
        holder.disciplineView.setText(CLab.getDiscipline());
        //holder.status.setImageDrawable();

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Toast.makeText(context, tasks.get(position).getName() + " is clicked!", Toast.LENGTH_SHORT).show();
        }
        });
        }
@Override
public int getItemCount() {
        return tasks.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    final TextView nameView;
    final TextView disciplineView;
    final TextView pointView;
    final TextView dateView;
    final ImageView status;
    final LinearLayout linearLayout;
    ViewHolder(View view){
        super(view);
        linearLayout = (LinearLayout) view.findViewById(R.id.oneItemLab);
        nameView = (TextView) view.findViewById(R.id.name);
        disciplineView = (TextView) view.findViewById((R.id.discipline));
        pointView = (TextView) view.findViewById((R.id.check_point));
        dateView = (TextView) view.findViewById(R.id.date_text);
        status = (ImageView) view.findViewById((R.id.status));
    }
}
}
