
package ru.psu.studyit.di.components

import dagger.BindsInstance
import ru.psu.studyit.CApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.psu.studyit.di.modules.*

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
        CModuleRepositories::class,
        CModuleRoom::class,
        CModuleViewModel::class,
        CModuleBindingActivity::class
    ]
)
interface IComponentApp                     : AndroidInjector<CApplication>
{
    @Component.Factory
    interface Factory
    {
        fun create(
            @BindsInstance application      : CApplication
        )                                   : IComponentApp
    }
}