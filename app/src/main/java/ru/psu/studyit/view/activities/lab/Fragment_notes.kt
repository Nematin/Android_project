//@author Дегтяникова Дарья
package ru.psu.studyit.view.activities.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.psu.studyit.R

import androidx.fragment.app.Fragment

class Fragment_notes : Fragment()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab_comments, container, false)
    }


}// Required empty public constructor
