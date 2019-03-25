package ru.psu.studyit.di.modules.activities

import dagger.Module
import dagger.Provides
import ru.psu.studyit.view.activities.CActivityMain

/********************************************************************************************************
 * Модуль dagger 2, отвечает за хранение и внедрение сылок на объекты активности [CActivityMain].       *
 * @author Селетков И.П. 2019 0312.                                                                     *
 *******************************************************************************************************/
@Module
class CModuleActivityMain
{
    @Provides
    fun provideActivitySettings(
        activity                            : CActivityMain
    )                                       : CActivityMain
    {
        return activity
    }
}