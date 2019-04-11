package ru.psu.studyit.view


import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.psu.studyit.view.activities.CActivityMain

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): CActivityMain
}