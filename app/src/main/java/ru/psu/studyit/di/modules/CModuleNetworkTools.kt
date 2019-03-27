package ru.psu.studyit.di.modules


import android.content.Context
import android.telephony.TelephonyManager
import ru.psu.studyit.utils.converters.CConverterLocalDateTime
import ru.psu.studyit.utils.converters.CConverterURI
import ru.psu.studyit.utils.converters.CConverterUUID
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/********************************************************************************************************
 * Модуль Dagger позволяет внедрять вспомогательные компоненты для обмена данными по сети.              *
 * @author Селетков И.П. 2018 0816.                                                                     *
 *******************************************************************************************************/
@Module
@Suppress("unused")
class CModuleNetworkTools
{
    @Provides
    @Singleton
    fun provideTelephonyManager(
        context                             : Context
    )                                       : TelephonyManager
    {
        return context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }
    /****************************************************************************************************
     * Возвращает объект Moshi со специальными конвертерами типов для дальнейшего использования в       *
     * библиотеке Retrofit.                                                                             *
     * @return объект Retrofit.                                                                         *
     ***************************************************************************************************/
    @Provides
    @Singleton
    internal fun provideMoshi()             : Moshi
    {
        return Moshi.Builder()
            //Для конвертирования UUID <> json string
            .add(CConverterUUID())
            //Для конвертирования LocalDateTime <> json string
            .add(CConverterLocalDateTime())
            .add(CConverterURI())
            .build()
    }
    /****************************************************************************************************
     * Возвращает объект OkHttpClient с настройками логирования для использования в                     *
     * библиотеке Retrofit.                                                                             *
     * @return объект Retrofit.                                                                         *
     ***************************************************************************************************/
    @Provides
    @Singleton
    internal fun provideOkHttpClient()      : OkHttpClient
    {
        val logging                         = HttpLoggingInterceptor()
        // set your desired log level
        logging.level                       = HttpLoggingInterceptor.Level.BODY

        val builder                         = OkHttpClient.Builder()
        // add your other interceptors …

        // add logging as last interceptor
        builder.addInterceptor(logging)

        return builder.build()
    }
}