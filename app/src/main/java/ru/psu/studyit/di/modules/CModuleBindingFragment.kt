package ru.psu.studyit.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psu.studyit.di.scopes.CScopeActivity
import ru.psu.studyit.view.activities.lab.CFragmentLabDetails

/********************************************************************************************************
 * Модуль dagger 2, отвечает за вставку зависимостей фрагментов в общий граф зависимостей.              *
 * @author Селетков И.П. 2019 0414.                                                                     *
 *******************************************************************************************************/
@Module
abstract class CModuleBindingFragment
{
    @CScopeActivity
    @ContributesAndroidInjector
    abstract fun contributeFragmentLabDetails(
    )                                       : CFragmentLabDetails
}