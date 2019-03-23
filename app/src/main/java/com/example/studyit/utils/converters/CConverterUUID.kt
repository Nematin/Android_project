package com.example.studyit.utils.converters


import androidx.annotation.Keep
import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

/********************************************************************************************************
 * Класс для конвертации между [UUID] и [String], для использования в БД и Json.                        *
 * @author Селетков И.П. 2018 0904.                                                                     *
 *******************************************************************************************************/
class CConverterUUID
{
    @Keep
    @TypeConverter
    @FromJson
    fun fromString(str                      : String
    )                                       : UUID?
    {
        if (str=="")
            return null
        return try
        {
             UUID.fromString(str)
        }
        catch(e                             : IllegalArgumentException)
        {
            null
        }
    }

    @Keep
    @TypeConverter
    @ToJson
    fun toString(uuid                       : UUID?
    )                                       : String
                                            = uuid?.toString() ?: ""
}