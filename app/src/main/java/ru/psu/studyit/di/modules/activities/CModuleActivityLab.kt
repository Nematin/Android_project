package ru.psu.studyit.di.modules.activities

import dagger.Module
import dagger.Provides
import ru.psu.studyit.view.activities.lab.CActivityLab

/********************************************************************************************************
 * Модуль dagger 2, отвечает за хранение и внедрение сылок на объекты активности [CActivityLab].        *
 * @author Селетков И.П. 2019 0312.                                                                     *
 *******************************************************************************************************/
@Module
class CModuleActivityLab
{
    @Provides
    fun provideActivityLab(
        activity                            : CActivityLab
    )                                       : CActivityLab
    {
        return activity
    }
}