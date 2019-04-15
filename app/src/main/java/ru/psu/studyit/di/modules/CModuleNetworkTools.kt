package ru.psu.studyit.di.modules


import android.content.Context
import android.telephony.TelephonyManager
import ru.psu.studyit.utils.converters.CConverterLocalDateTime
import ru.psu.studyit.utils.converters.CConverterURI
import ru.psu.studyit.utils.converters.CConverterUUID
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.psu.studyit.CApplication
import java.io.File
import java.util.concurrent.TimeUnit
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
     * Возвращает объект [Cache] для кэширования сетевых запросов.                                      *
     * @return объект [Cache].                                                                          *
     ***************************************************************************************************/
    @Provides
    @Singleton
    internal fun provideCache(
        application                         : CApplication
    )                                       : Cache
    {
        val cacheSize                       = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory              = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }
    /****************************************************************************************************
     * Возвращает объект OkHttpClient с настройками логирования для использования в                     *
     * библиотеке Retrofit.                                                                             *
     * @return объект Retrofit.                                                                         *
     ***************************************************************************************************/
    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        cache                               : Cache
    )                                       : OkHttpClient
    {
        val logging                         = HttpLoggingInterceptor()
        // set your desired log level
        logging.level                       = HttpLoggingInterceptor.Level.BODY

        val builder                         = OkHttpClient.Builder()
        builder.cache(cache)
        builder.addInterceptor(logging)
        //Там никакого специфичного функционала нет, зачем он нужен?
       // builder.addNetworkInterceptor(RequestInterceptor())
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        return builder.build()
    }
}