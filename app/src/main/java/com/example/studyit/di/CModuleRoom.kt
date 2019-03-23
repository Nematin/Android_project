package com.example.studyit.di


import android.content.Context
import com.example.studyit.data.dao.IDAOSubject
import com.example.studyit.utils.database.CRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/********************************************************************************************************
 * Модуль Dagger позволяет внедрять ссылки на объект-базу данных в места, где это запрашивается.        *
 * @see "https://medium.com/@marco_cattaneo/integrate-dagger-2-with-room-persistence-library-in-        *
 * few-lines-abf48328eaeb"                                                                              *
 * @author Селетков И.П. 2018 0816.                                                                     *
 *******************************************************************************************************/
@Module
@Suppress("unused")
class CModuleRoom
{
    /****************************************************************************************************
     * Возвращает ссылку на базу данных.                                                                *
     * @param context - контекст выполнения приложения.                                                 *
     ***************************************************************************************************/
    @Singleton
    @Provides
    fun providesRoomDatabase(
        context                             : Context
    )                                       : CRoomDatabase
    {
        return CRoomDatabase.getInstance(context)
    }

    /****************************************************************************************************
     * Возвращает ссылку на объект доступа к данным типа CAttributeValue.                               *
     * @param database - база данных, открытая с помощью room.                                          *
     ***************************************************************************************************/
    @Singleton
    @Provides
    fun providesAttributeValueDAO(
        database                            : CRoomDatabase
    )                                       : IDAOSubject
    {
        return database.daoSubject()
    }
}