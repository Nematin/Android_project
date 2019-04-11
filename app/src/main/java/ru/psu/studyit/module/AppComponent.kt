package ru.psu.studyit.module

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ru.psu.studyit.AppController
import ru.psu.studyit.view.ActivityModule
import ru.psu.studyit.view.ViewModelModule
import javax.inject.Singleton

@Component(
        modules = [
            ApiModule::class,
            DbModule::class,
            ViewModelModule::class,
            ActivityModule::class,
            AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    /*
     * This is our custom Application class
     * */
    fun inject(appController: AppController)
}