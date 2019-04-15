//@author Баландин, Дегтяникова
package ru.psu.studyit.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import ru.psu.studyit.R
import ru.psu.studyit.model.CLab

import androidx.recyclerview.widget.RecyclerView

class ViewMainFragmentAdaptor internal constructor(
    private val context: Context, private val tasks: List<CLab>
) : RecyclerView.Adapter<CRecyclerViewAdapterLabs.ViewHolder>()
{

    private val inflater: LayoutInflater

    init
    {
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CRecyclerViewAdapterLabs.ViewHolder
    {

        val view = inflater.inflate(R.layout.fragment_lab_details, parent, false)
        return CRecyclerViewAdapterLabs.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CRecyclerViewAdapterLabs.ViewHolder, position: Int)
    {
        val CLab = tasks[position]
        holder.nameView.text = CLab.name
        holder.disciplineView.text = CLab.discipline
        //holder.status.setImageDrawable();

        holder.linearLayout.setOnClickListener {
            Toast.makeText(context, tasks[position].name!! + " is clicked!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getItemCount(): Int
    {
        return tasks.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view)
    {
        internal val nameView: TextView
        internal val disciplineView: TextView
        internal val pointView: TextView
        internal val dateView: TextView
        internal val status: ImageView
        internal val linearLayout: LinearLayout

        init
        {
            linearLayout = view.findViewById<View>(R.id.oneItemLab) as LinearLayout
            nameView = view.findViewById<View>(R.id.name) as TextView
            disciplineView = view.findViewById<View>(R.id.discipline) as TextView
            pointView = view.findViewById<View>(R.id.check_point) as TextView
            dateView = view.findViewById<View>(R.id.date_text) as TextView
            status = view.findViewById<View>(R.id.status) as ImageView
        }
    }
}
