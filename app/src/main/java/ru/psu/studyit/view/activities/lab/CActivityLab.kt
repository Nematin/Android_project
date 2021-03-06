package ru.psu.studyit.view.activities.lab

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_lab.*

import ru.psu.studyit.view.activities.CActivityBase
import ru.psu.studyit.viewmodel.CViewModelActivityLab
import ru.psu.studyit.databinding.ActivityLabBinding
import javax.inject.Inject
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.R



/********************************************************************************************************
 * Активность                                                                                           *
 * отображает данные по одной контрольной точке                                                         *
 * позволяет создать и отправить отчёт по КТ, отслеживать статус его проверки.                          *
 * @author Баландин А.Д. 2019 0201.                                                                     *
 *******************************************************************************************************/
class CActivityLab                          :
    CActivityBase()
{
    @Inject
    lateinit var viewModelFactory           : ViewModelProvider.Factory
    private lateinit var viewModel          : CViewModelActivityLab

    /****************************************************************************************************
     * Создание формы.                                                                                  *
     ***************************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        initViewModel()
        initDataBinding()
        initControls()

    }
    /****************************************************************************************************
     * Создание и настройка модели представления.                                                       *
     ***************************************************************************************************/
    private fun initViewModel()
    {
        //Создание модели представления.
        viewModel                           = viewModel(viewModelFactory) {
            //При изменении состояния сервиса вызываем обработчик.
//            observe(this.serviceGeolocationActive, ::onServiceGeolocationActiveChanged)
        }
    }

    /****************************************************************************************************
     * Настройка компонентов для автоматического обновления информации на экране.                       *
     ***************************************************************************************************/
    private fun initDataBinding()
    {
        // Inflate view and obtain an instance of the binding class.
        val binding                         : ActivityLabBinding
                                            = DataBindingUtil.setContentView(this, ru.psu.studyit.R.layout.activity_lab)
        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner              = this

        binding.viewModel                   = viewModel

//        setSupportActionBar(binding.layoutToolbar.toolbar)
    }

    /****************************************************************************************************
     * Получение ссылок на управляющие элементы формы.                                                  *
     * Начальные значения.                                                                              *
     ***************************************************************************************************/
    private fun initControls()
    {
        viewPagerActivityLab.adapter        = CPagerAdapterFragmentActivityLab(this, supportFragmentManager)
        return
    }


    fun saveLab(view: View)
    {
        //Скорее всего здесь будет код для сохранения лабораторной

        val toast = Toast.makeText(
            applicationContext,
            "ДОБААААВЬ меня", Toast.LENGTH_SHORT
        )
        toast.show()

    }


}
