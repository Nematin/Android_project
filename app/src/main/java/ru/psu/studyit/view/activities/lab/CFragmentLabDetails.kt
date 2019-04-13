package ru.psu.studyit.view.activities.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_lab_details.*

import ru.psu.studyit.R
import ru.psu.studyit.view.activities.CFragmentBase
import ru.psu.studyit.viewmodel.CViewModelActivityLab
import javax.inject.Inject

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/********************************************************************************************************
 * Фрагмент отображает параметры одной лабораторной работы.                                             *
 * @author Дегтяникова Д. 2019 0201.                                                                    *
 *******************************************************************************************************/
class CFragmentLabDetails                   : CFragmentBase()
{
    @Inject
    lateinit var viewModelFactory           : ViewModelProvider.Factory
    private var viewModel                   : CViewModelActivityLab?
                                            = null
    /****************************************************************************************************
     * Создание фрагмента.                                                                              *
     ***************************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        initViewModel()
    }
    /****************************************************************************************************
     * Создание и настройка модели представления.                                                       *
     ***************************************************************************************************/
    private fun initViewModel()
    {
        //Создание модели представления.
        activity?.run {
            viewModel                       = viewModel(viewModelFactory) {
                //При изменении состояния сервиса вызываем обработчик.
                //observe(this.serviceGeolocationActive, ::onServiceGeolocationActiveChanged)
            }
        }

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
        // Inflate the layout for this fragment
        val binding                         = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_lab_details,
            container,
            false
        )
        val view                            = binding.root
        //here data must be an instance of the class MarsDataProvider
        viewModel?.run {
            binding.viewModel               = viewModel
        }

        return view
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

        val adapter                         = ArrayAdapter(context, android.R.layout.simple_spinner_item, items)
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Применяем адаптер к элементу spinner
        spinnerSubject.adapter              = adapter
    }

}
