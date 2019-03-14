package com.example.studyit.utils.api

import com.example.studyit.model.CSubject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/********************************************************************************************************
 * Интерфейс описывает запросы к API сервера.                                                           *
 * @author Селетков И.П. 2019 0211.                                                                     *
 *******************************************************************************************************/
interface IServerAPITemplate
{
    /****************************************************************************************************
     * Запрос списка дисциплин, которые изучает студент с учётной записью с идентификатором [userId].   *
     * по данным АСУ ТП.                                                                                *
     * @param userId - идентификатор учётной записи пользователя.                                       *
     * @return объект с возможность отслеживания статуса запроса.                                       *
     ***************************************************************************************************/
    @GET("subjects")
    fun getSubjectsByUserId(
        @Query("user_id")
        userId                              : String
    )                                       : Single<List<CSubject>>
}