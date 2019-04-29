
package ru.psu.studyit.di.modules


import ru.psu.studyit.model.CLab
import ru.psu.studyit.model.CSubject
import ru.psu.studyit.model.CCheckPoint
import dagger.Binds
import dagger.Module
import ru.psu.studyit.data.repositories.*

/********************************************************************************************************
 * Модуль Dagger позволяет внедрять ссылки на сервисы работы с данными.                                 *
 * @author Селетков И.П. 2018 0818.                                                                     *
 *******************************************************************************************************/
@Module
@Suppress("unused")
abstract class CModuleRepositories
{
    /****************************************************************************************************
     * Возвращает ссылку на репозиторий для работы с данными типа [CSubject].                           *
     * @return репозиторий для работы с данными.                                                        *
     ***************************************************************************************************/
    @Binds
    abstract fun repositorySubject(
        service                             : CRepositorySubject
    )                                       : IRepositorySubject

    /****************************************************************************************************
     * Возвращает ссылку на репозиторий для работы с данными типа [CLab].                               *
     * @return репозиторий для работы с данными.                                                        *
     ***************************************************************************************************/
    @Binds
    abstract fun repositoryLab(
            service                         : CRepositoryLab
    )                                       : IRepositoryLab

    /****************************************************************************************************
     * Возвращает ссылку на репозиторий для работы с данными типа [CCheckPoint].                               *
     * @return репозиторий для работы с данными.                                                        *
     ***************************************************************************************************/
    @Binds
    abstract fun repositoryCheckPoint(
            service                         : CRepositoryCheckPoint
    )                                       : IRepositoryCheckPoint
}