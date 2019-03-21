//@author Баландин, Дегтяникова
package com.example.studyit.di

import com.example.studyit.CApplication
import com.example.studyit.di.modules.CModuleApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

/********************************************************************************************************
 * Компонент dagger 2, отвечает за хранение и внедрение сылок на все объекты, описанные в модулях.      *
 * https://medium.com/@Zhuinden/that-missing-guide-how-to-use-dagger2-ef116fbea97                       *
 * https://android.jlelse.eu/7-steps-to-implement-dagger-2-in-android-dabc16715a3a                      *
 * @author Селетков И.П. 2018 0816.                                                                     *
 *******************************************************************************************************/
@Singleton
@Component(
    modules                                 = [
        AndroidSupportInjectionModule::class,
        CModuleApplication::class,
        CModuleNetworkTools::class,
        CModuleServerAPI::class,
        CModuleDataService::class,
        CModuleRoom::class
    ]
)
interface IComponentApp                     : AndroidInjector<CApplication>
{
    @Component.Builder
    abstract class Builder                  : AndroidInjector.Builder<CApplication>() {}
}
