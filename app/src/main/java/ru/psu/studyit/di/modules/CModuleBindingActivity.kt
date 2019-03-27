package ru.psu.studyit.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psu.studyit.di.CScopeActivity
import ru.psu.studyit.di.modules.activities.CModuleActivityLogin
import ru.psu.studyit.di.modules.activities.CModuleActivityMain
import ru.psu.studyit.view.activities.CActivityLogin
import ru.psu.studyit.view.activities.CActivityMain
import ru.psu.studyit.view.activities.lab.CActivityLab

/********************************************************************************************************
 * Модуль dagger 2, отвечает за привязку объектов активностей по названиям их классов.                  *
 * @author Селетков И.П. 2019 0223.                                                                     *
 *******************************************************************************************************/
@Module
abstract class CModuleBindingActivity
{
    @CScopeActivity
    @ContributesAndroidInjector(
        modules                             = [
            CModuleActivityLogin::class
        ]
    )
    internal abstract fun bindActivityMain(
    )                                       : CActivityLogin

    @CScopeActivity
    @ContributesAndroidInjector(
        modules                             = [
            CModuleActivityMain::class
        ]
    )
    internal abstract fun bindActivitySettings(
    )                                       : CActivityMain

    @CScopeActivity
    @ContributesAndroidInjector(
        modules                             = [
            CModuleActivityMain::class
        ]
    )
    internal abstract fun bindActivityLab(
    )                                       : CActivityLab
}