//@author Дегтяникова Дарья
package ru.psu.studyit.view.activities.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_lab_details.*

import ru.psu.studyit.R

class CFragmentLabDetails                   : Fragment()
{
    internal var items                      = arrayOf("1 предмет", "2 предмет", "3 предмет", "4 предмет")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater                            : LayoutInflater,
        container                           : ViewGroup?,
        savedInstanceState: Bundle?
    )                                       : View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val adapter                         = ArrayAdapter(context, android.R.layout.simple_spinner_item, items)
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Применяем адаптер к элементу spinner
        spinnerSubject.adapter              = adapter
    }

}
