package ru.psu.studyit.di.modules.activities

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psu.studyit.view.activities.lab.CActivityLab
import ru.psu.studyit.view.activities.lab.CFragmentLabDetails

/********************************************************************************************************
 * Модуль dagger 2, отвечает за хранение и внедрение сылок на объекты активности [CActivityLab].        *
 * @author Селетков И.П. 2019 0312.                                                                     *
 *******************************************************************************************************/
@Module
abstract class CModuleActivityLab
{
    @ContributesAndroidInjector
    abstract fun contributeFragmentLabDetails(
    )                                       : CFragmentLabDetails
}