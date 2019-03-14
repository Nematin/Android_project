package com.example.studyit.utils.converters


import androidx.annotation.Keep
import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter


import java.util.*

/********************************************************************************************************
 * Класс для конвертации между [LocalDateTime] и [String], для использования в БД и Json.               *
 * @author Селетков И.П. 2018 0904.                                                                     *
 *******************************************************************************************************/
class CConverterLocalDateTime
{
    companion object
    {
        private val formatterDate           = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale("ru_RU"))
        private val formatterTime           = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("ru_RU"))
        private val formatterTimeFile       = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm", Locale("ru_RU"))

        private val formatterTimeBD         = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n", Locale("ru_RU"))

        /****************************************************************************************************
         * Конвертирует строку в объект "Дата" по шаблону, указанному выше.                                 *
         * @param time - строка, представляющая дату.                                                       *
         * @return - объект типа Date.                                                                      *
         ***************************************************************************************************/
        @Keep
        @TypeConverter
        @FromJson
        @JvmStatic
        fun stringToTime(time               : String)
                                            : LocalDateTime
        {
            return LocalDateTime.parse(time, formatterTime)
        }
        @JvmStatic
        fun stringToTimeBD(time             : String)
                                            : LocalDateTime
        {
            return LocalDateTime.parse(time, formatterTimeBD)
        }
        /****************************************************************************************************
         * Конвертирует объект "Дата" в строку по шаблону, указанному выше.                                 *
         * @param time - объект типа Date.                                                                  *
         * @return - строка, представляющая дату.                                                           *
         ***************************************************************************************************/
        @Keep
        @TypeConverter
        @ToJson
        @JvmStatic
        fun timeToString(time               : LocalDateTime?)
                                            : String
        {
            if (time==null)
                return ""
            return formatterTime.format(time)
        }

        /****************************************************************************************************
         * Конвертирует строку в объект "Дата" по шаблону, указанному выше.                                 *
         * @param date - строка, представляющая дату.                                                       *
         * @return - объект типа Date.                                                                      *
         ***************************************************************************************************/
        @JvmStatic
        fun stringToDate(date : String)     : LocalDateTime
        {
            return LocalDateTime.parse(date, formatterDate)
        }
        /****************************************************************************************************
         * Конвертирует объект "Дата" в строку по шаблону, указанному выше.                                 *
         * @param date - объект типа Date.                                                                  *
         * @return - строка, представляющая дату.                                                           *
         ***************************************************************************************************/
        @JvmStatic
        fun dateToString(date : LocalDateTime?)
                : String
        {
            if (date==null)
                return ""
            return formatterDate.format(date)
        }

        /****************************************************************************************************
         * Конвертирует объект "Дата" в строку по шаблону "yyyy-MM-dd HH.mm"                                *
         ***************************************************************************************************/
        @JvmStatic
        fun timeToStringFile(time           : LocalDateTime?)
                : String
        {
            if (time==null)
                return ""
            return formatterTimeFile.format(time)
        }
        /****************************************************************************************************
         * Конвертирует объект "Дата" в строку по шаблону "yyyy-MM-dd HH.mm"                                *
         ***************************************************************************************************/
        @JvmStatic
        fun millisecondsToTime(milliseconds : Long)
                                            : LocalDateTime
        {
            return ZonedDateTime.ofInstant(Instant.ofEpochSecond(milliseconds), ZoneOffset.UTC).toLocalDateTime()
        }

        /****************************************************************************************************
         * Конвертирует объект "Дата" в строку по шаблону "yyyy-MM-dd HH.mm"                                *
         ***************************************************************************************************/
        @JvmStatic
        fun timeToMilliseconds(time         : LocalDateTime?)
                                            : Long
        {
            time ?: return 0
            return time.atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
        }
    }
}
