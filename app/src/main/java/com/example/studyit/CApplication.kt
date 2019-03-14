package com.example.studyit

import com.example.studyit.di.IComponentApp
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/********************************************************************************************************
 * Основной класс приложения (точка входа выполнения).                                                  *
 * @author Селетков И.П. 2019 0212.                                                                     *
 *******************************************************************************************************/
class CApplication                          : DaggerApplication()
{
    companion object
    {
        @JvmStatic
        lateinit var componentApp           : IComponentApp
            private set
    }

    override fun applicationInjector()      : AndroidInjector<out CApplication>
    {
        initAppComponent()
        return componentApp
    }
    /****************************************************************************************************
     * Настройка компонента для внедрения в других местах.                                              *
     * https://android.jlelse.eu/new-android-injector-with-dagger-2-part-3-fe3924df6a89                 *
     ***************************************************************************************************/
    private fun initAppComponent()
    {
        componentApp                        = DaggerIComponentApp
            .builder()
            .create(this) as IComponentApp
    }
}