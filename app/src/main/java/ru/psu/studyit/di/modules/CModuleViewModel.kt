package ru.psu.studyit.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.psu.studyit.viewmodel.CViewModelActivityLab
import ru.psu.studyit.viewmodel.CViewModelActivityMain
import ru.psu.studyit.viewmodel.CViewModelFactory
import ru.psu.studyit.viewmodel.CViewModelKey

/********************************************************************************************************
 * Модуль Dagger позволяет внедрять зависимости на модели представлений.                                *
 * @author Селетков И.П. 2018 0830.                                                                     *
 *******************************************************************************************************/
@Module
@Suppress("unused")
abstract class CModuleViewModel
{
    @Binds
    internal abstract fun bindViewModelFactory(
        factory                             : CViewModelFactory
    )                                       : ViewModelProvider.Factory

    /****************************************************************************************************
     * Подготавливает модель представления для главной активности.                                      *
     * @return модель представления главной активности.                                                 *
     ***************************************************************************************************/
    @Binds
    @IntoMap
    @CViewModelKey(CViewModelActivityMain::class)
    internal abstract fun bindsViewModelActivityMain(
        viewModelActivityMain               : CViewModelActivityMain
    )                                       : ViewModel

    /****************************************************************************************************
     * Подготавливает модель представления для активности лабораторной работы.                          *
     * @return модель представления активности лабораторной работы.                                     *
     ***************************************************************************************************/
    @Binds
    @IntoMap
    @CViewModelKey(CViewModelActivityLab::class)
    internal abstract fun bindsViewModelActivityLab(
        viewModelActivityLab                : CViewModelActivityLab
    )                                       : ViewModel
}