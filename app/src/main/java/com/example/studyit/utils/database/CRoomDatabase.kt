package com.example.studyit.utils.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studyit.data.dao.IDAOSubject
import com.example.studyit.model.CSubject
import com.example.studyit.utils.converters.CConverterLocalDateTime
import com.example.studyit.utils.converters.CConverterURI
import com.example.studyit.utils.converters.CConverterUUID

/********************************************************************************************************
 * Класс отвечает за связь объектов доступа к данным и объектов сущностей с базой данных SQLite.        *
 * @author Селетков И.П. 2018 0816.                                                                     *
 *******************************************************************************************************/
@Database(
    entities                                = [
            CSubject::class
    ],
    version                                 = 1,
    exportSchema                            = false
)
@TypeConverters(
        value                               =
        [
            CConverterLocalDateTime::class,
            CConverterUUID::class,
            CConverterURI::class
        ]
)
abstract class CRoomDatabase                : RoomDatabase()
{
    companion object
    {
        @Volatile
        private var INSTANCE                : CRoomDatabase?
                                            = null

        fun getInstance(
            context                         : Context
        )                                   =
            INSTANCE ?: synchronized(this)
            {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE                = it
                }
            }


        private fun buildDatabase(
            context                         : Context
        )                                   =
            Room.databaseBuilder(
                context.applicationContext,
                CRoomDatabase::class.java,
                //"${context.filesDir.absolutePath}/databases/database.db"
                "database.db"
            )
                .build()

        fun destroyInstance()
        {
            INSTANCE                        = null
        }
    }
    abstract fun daoSubject()               : IDAOSubject
}