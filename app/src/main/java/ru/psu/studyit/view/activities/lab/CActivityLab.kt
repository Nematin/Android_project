package ru.psu.studyit.view.activities.lab

import android.os.Bundle
import android.view.View
import android.widget.Toast

import ru.psu.studyit.R

import ru.psu.studyit.view.activities.CActivityBase
import kotlinx.android.synthetic.main.activity_lab.*


/********************************************************************************************************
 * Активность                                                                                           *
 * отображает данные по одной контрольной точке                                                         *
 * позволяет создать и отправить отчёт по КТ, отслеживать статус его проверки.                          *
 * @author Баландин А.Д. 2019 0201.                                                                     *
 *******************************************************************************************************/
class CActivityLab                          :
    CActivityBase()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab)

        viewPagerActivityLab.adapter        = PagerAdapter(this, supportFragmentManager, 4)
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
