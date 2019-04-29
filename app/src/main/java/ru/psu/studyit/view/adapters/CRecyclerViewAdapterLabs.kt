//@author Баландин, Дегтяникова, Балышев
package ru.psu.studyit.view.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast


import ru.psu.studyit.R

import java.util.ArrayList

import androidx.recyclerview.widget.RecyclerView

import ru.psu.studyit.model.CLab
import ru.psu.studyit.view.activities.lab.CActivityLab

import androidx.constraintlayout.widget.Constraints.TAG


class CRecyclerViewAdapterLabs(private val context: Context) :
    RecyclerView.Adapter<CRecyclerViewAdapterLabs.ViewHolder>()
{

    private val inflater: LayoutInflater
    val labs: ArrayList<CLab>

    init
    {
        this.labs = ArrayList()
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CRecyclerViewAdapterLabs.ViewHolder
    {

        val view = inflater.inflate(R.layout.recycler_view_tasks_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CRecyclerViewAdapterLabs.ViewHolder, position: Int)
    {
        val lab = labs[position]
        holder.nameView.text = lab.name
        holder.disciplineView.text = lab.discipline

        holder.linearLayout.setOnClickListener {
            // Toast.makeText(context, tasks.get(position).getName() + " is clicked!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onClick: clicked on: " + labs[position])

            Toast.makeText(context, labs[position].toString() + " is clicked!", Toast.LENGTH_SHORT)
                .show()

            val intent = Intent(context, CActivityLab::class.java)
            //intent.putExtra("image_url", tasks.get(position));
            //intent.putExtra("image_name", mImageNames.get(position));
            context.startActivity(intent)
        }
    }

    //    public void on(View view)
    //    {
    //        Intent intent = new Intent(this, .class);
    //        startActivity(intent);
    //    }
    override fun getItemCount(): Int
    {
        return labs.size
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view)
    {
        internal val disciplineView: TextView
        internal val nameView: TextView
        internal val pointView: TextView
        internal val status: ImageView
        internal val linearLayout: LinearLayout

        init
        {
            linearLayout = view.findViewById(R.id.oneRow)
            nameView = view.findViewById(R.id.name)
            disciplineView = view.findViewById(R.id.discipline)
            pointView = view.findViewById(R.id.check_point)
            status = view.findViewById(R.id.status)
        }
    }
}
