package ru.psu.studyit.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.psu.studyit.data.dao.SubjectDao
import ru.psu.studyit.view.activities.DataBase.AppDatabase

import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
                application, AppDatabase::class.java, "Main.db")
                .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideSubjectDao(appDatabase: AppDatabase): SubjectDao {
        return appDatabase.movieDao()
    }
}