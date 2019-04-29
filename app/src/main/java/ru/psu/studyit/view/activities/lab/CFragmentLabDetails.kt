package ru.psu.studyit.view.activities.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import ru.psu.studyit.R
import ru.psu.studyit.view.activities.CFragmentBase
import ru.psu.studyit.viewmodel.CViewModelActivityLab
import javax.inject.Inject

import androidx.databinding.DataBindingUtil
import ru.psu.studyit.databinding.FragmentLabDetailsBinding


/********************************************************************************************************
 * Фрагмент отображает параметры одной лабораторной работы.                                             *
 * @author Дегтяникова Д. 2019 0201.                                                                    *
 *******************************************************************************************************/
class CFragmentLabDetails                   : CFragmentBase()
{
    @Inject
    lateinit var viewModelFactory           : ViewModelProvider.Factory
    private lateinit var viewModel          : CViewModelActivityLab

    /****************************************************************************************************
     * Создание фрагмента.                                                                              *
     ***************************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }
    /****************************************************************************************************
     * Создание и настройка модели представления.                                                       *
     ***************************************************************************************************/
    private fun initViewModel()
    {
        //Создание модели представления.
        viewModel                       = viewModel(viewModelFactory) {
            //При изменении состояния сервиса вызываем обработчик.
            //observe(this.serviceGeolocationActive, ::onServiceGeolocationActiveChanged)
        }!!

    }
    /****************************************************************************************************
     * Создание внешнего вида фрагмента.                                                                *
     ***************************************************************************************************/
    override fun onCreateView(
        inflater                            : LayoutInflater,
        container                           : ViewGroup?,
        savedInstanceState: Bundle?
    )                                       : View?
    {
        initViewModel()
        // Inflate the layout for this fragment
        return  DataBindingUtil.inflate<FragmentLabDetailsBinding>(
            inflater,
            R.layout.fragment_lab_details,
            container,
            false
        ).also {
            it.viewModel                    = viewModel
            it.lifecycleOwner               = this
           // it.listeners                    = viewModel
        }.root
    }
    /****************************************************************************************************
     * Завершение создания внешнего вида фрагмента.                                                     *
     ***************************************************************************************************/
    override fun onViewCreated(
        view                                : View,
        savedInstanceState                  : Bundle?
    )
    {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

//        val adapter                         = ArrayAdapter(context, android.R.layout.simple_spinner_item, items)
//        // Определяем разметку для использования при выборе элемента
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        // Применяем адаптер к элементу spinner
//        spinnerSubject.adapter              = adapter
    }

}
