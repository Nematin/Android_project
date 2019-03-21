//@author Баландин, Дегтяникова
package com.example.studyit.di


import com.example.studyit.data.services.CServiceLab
import com.example.studyit.data.services.CServiceSubject
import com.example.studyit.data.services.IServiceLab
import com.example.studyit.data.services.IServiceSubject
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
     * Возвращает ссылку на сервис для работы с данными типа [CSubject].                                *
     * @return сервис для работы с данными.                                                             *
     ***************************************************************************************************/
    @Binds
    abstract fun serviceLab(
            service                             : CServiceLab
    )                                       : IServiceLab
}
