package com.example.studyit.utils.converters


import androidx.annotation.Keep
import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.io.File

/********************************************************************************************************
 * Класс для конвертации между [File] и [String], для использования в БД и Json.                        *
 * @author Селетков И.П. 2018 0927.                                                                     *
 *******************************************************************************************************/
class CConverterFile
{
    @Keep
    @TypeConverter
    @FromJson
    fun fromString(str                      : String
    )                                       : File?
    {
        if (str=="")
            return null

        return try
        {
            File(str)
        }
        catch(e                             : IllegalArgumentException)
        {
//            CApplicationOld.WriteLog("E0158. String is not uuid: $str",
//                                     CApplicationOld.LogFileType.Errors,
//                                     e)
            null
        }
    }

    @Keep
    @TypeConverter
    @ToJson
    fun toString(file                       : File?
    )                                       : String
                                            = file?.toString() ?: ""
}