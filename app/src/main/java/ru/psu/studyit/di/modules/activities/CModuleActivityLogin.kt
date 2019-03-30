package ru.psu.studyit.di.modules.activities

import dagger.Module
import dagger.Provides
import ru.psu.studyit.view.activities.CActivityLogin

/********************************************************************************************************
 * Модуль dagger 2, отвечает за хранение и внедрение сылок на объекты активности [CActivityLogin].      *
 * @author Селетков И.П. 2019 0223.                                                                     *
 *******************************************************************************************************/
@Module
class CModuleActivityLogin
{
    @Provides
    fun provideActivityLogin(
        activity                            : CActivityLogin
    )                                       : CActivityLogin
    {
        return activity
    }
}