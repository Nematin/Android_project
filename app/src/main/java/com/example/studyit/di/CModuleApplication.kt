//@author Баландин, Дегтяникова
package com.example.studyit.di.modules


import android.content.Context
import dagger.Binds
import dagger.Module

/********************************************************************************************************
 * Модуль Dagger позволяет внедрять ссылки на объект-приложение в места, где это запрашивается.         *
 * https://medium.com/@marco_cattaneo/integrate-dagger-2-with-room-persistence-library-in-              *
 * few-lines-abf48328eaeb                                                                               *
 * @author Селетков И.П. 2018 0816.                                                                     *
 *******************************************************************************************************/
@Module
abstract class CModuleApplication {

    @Binds
    abstract fun provideContext(
        app                                 : CApplication
    )                                       : Context

}
