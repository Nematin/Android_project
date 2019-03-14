package com.example.studyit.utils.converters


import android.net.Uri
import androidx.annotation.Keep
import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

/********************************************************************************************************
 * Класс для конвертации между [Uri] и [String], для использования в БД и Json.                         *
 * @author Селетков И.П. 2018 0927.                                                                     *
 *******************************************************************************************************/
class CConverterURI
{
    /****************************************************************************************************
     * Формирует объект из строки.                                                                      *
     * @param str - строка.                                                                             *
     * @return объект.                                                                                  *
     ***************************************************************************************************/
    @Keep
    @TypeConverter
    @FromJson
    fun fromString(str                      : String
    )                                       : Uri?
    {
        if (str=="")
            return null

        return try
        {
            Uri.parse(str)
        }
        catch(e                             : IllegalArgumentException)
        {
//            CApplicationOld.WriteLog("E0158. String is not uuid: $str",
//                                     CApplicationOld.LogFileType.Errors,
//                                     e)
            null
        }
    }
    /****************************************************************************************************
     * Формирует строку из объекта.                                                                     *
     * @param uri - объект.                                                                             *
     * @return строка.                                                                                  *
     ***************************************************************************************************/
    @Keep
    @TypeConverter
    @ToJson
    fun toString(uri                        : Uri?
    )                                       : String
                                            = uri?.toString() ?: ""
}