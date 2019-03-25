
package ru.psu.studyit.di.modules


import ru.psu.studyit.data.services.CServiceLab
import ru.psu.studyit.data.services.CServiceSubject
import ru.psu.studyit.data.services.IServiceLab
import ru.psu.studyit.data.services.IServiceSubject
import ru.psu.studyit.model.CLab
import ru.psu.studyit.model.CSubject
import dagger.Binds
import dagger.Module

/********************************************************************************************************
 * Модуль Dagger позволяет внедрять ссылки на сервисы работы с данными.                                 *
 * @author Селетков И.П. 2018 0818.                                                                     *
 *******************************************************************************************************/
@Module
@Suppress("unused")
abstract class CModuleDataService
{
    /****************************************************************************************************
     * Возвращает ссылку на сервис для работы с данными типа [CSubject].                                *
     * @return сервис для работы с данными.                                                             *
     ***************************************************************************************************/
    @Binds
    abstract fun serviceSubject(
        service                             : CServiceSubject
    )                                       : IServiceSubject

    /****************************************************************************************************
     * Возвращает ссылку на сервис для работы с данными типа [CLab].                                    *
     * @return сервис для работы с данными.                                                             *
     ***************************************************************************************************/
    @Binds
    abstract fun serviceLab(
            service                         : CServiceLab
    )                                       : IServiceLab
}
