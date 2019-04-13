package ru.psu.studyit.di.modules

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit.converter.java8.Java8OptionalConverterFactory
import ru.psu.studyit.utils.api.CServiceServerAPI
import ru.psu.studyit.utils.api.IServerAPITemplate
import ru.psu.studyit.utils.api.IServiceServerAPI
import javax.inject.Named
import javax.inject.Singleton

/********************************************************************************************************
 * Модуль Dagger позволяет внедрять ссылки на функционал обмена данными с API сервера.                  *
 * https://medium.com/@marco_cattaneo/integrate-dagger-2-with-room-persistence-library-in-              *
 * few-lines-abf48328eaeb                                                                               *
 * https://proandroiddev.com/mvvm-with-kotlin-android-architecture-components-dagger-2-retrofit-        *
 * and-rxandroid-1a4ebb38c699                                                                           *
 * @author Селетков И.П. 2018 0816.                                                                     *
 *******************************************************************************************************/
@Module
@Suppress("unused")
class CModuleServerAPI
{
    /****************************************************************************************************
     * Возвращает объект из библиотеки Retrofit.                                                        *
     * @return объект Retrofit.                                                                         *
     ***************************************************************************************************/
    @Provides
    @Singleton
    @Named("DataServerAPI")
    internal fun provideRetrofitInterface(
        moshi                               : Moshi,
        okHttpClient                        : OkHttpClient
    )                                       : Retrofit
    {
        val url                             = "http://82.118.128.112:12002/"

        return Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(Java8OptionalConverterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(okHttpClient)
                    .build()
    }
    /****************************************************************************************************
     * Построение шаблона запросов Retrofit для работы с API сервера.                                   *
     * @param retrofit - объект из библиотеки Retrofit, позволяющий создать объект работы с API по      *
     * его интерфейсу.                                                                                  *
     * @return шаблон запросов к API.                                                                   *
     ***************************************************************************************************/
    @Provides
    @Singleton
    internal fun provideServerAPITemplate(
        @Named("DataServerAPI") retrofit
                                            : Retrofit
    )                                       : IServerAPITemplate
    {
        return retrofit.create(IServerAPITemplate::class.java)
    }
    /****************************************************************************************************
     * Построение сервиса для работы с API сервера.                                                     *
     * @param template - шаблон запросов retrofit к конкретному API.                                    *
     * @return сервис для работы с API.                                                                 *
     ***************************************************************************************************/
    @Provides
    @Singleton
    internal fun provideServerAPIService(
        template                            : IServerAPITemplate
    )                                       : IServiceServerAPI
    {
        return CServiceServerAPI(template)
    }
}

