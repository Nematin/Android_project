package ru.psu.studyit.utils.api

import retrofit2.http.GET
import io.reactivex.Observable;

/********************************************************************************************************
 * Интерфейс описывает методы сервиса для работы с API сервера смарт-обходчик.                          *
 * @author Селетков И.П. 2018 0816.                                                                     *
 *******************************************************************************************************/
interface IServiceServerAPI
{
    @GET("subjects")
    fun fetchSubjects(): Observable<SubjectApiResponse>
}