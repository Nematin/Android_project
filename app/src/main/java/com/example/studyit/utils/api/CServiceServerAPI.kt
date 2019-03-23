package com.example.studyit.utils.api

import com.example.studyit.model.CSubject
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/********************************************************************************************************
 * Сервис отвечает за взаимодействие с API сервера данных.                                              *
 * @author Селетков И.П. 2018 0817.                                                                     *
 *******************************************************************************************************/
@Singleton
class CServiceServerAPI
@Inject constructor
(
    private val template                    : IServerAPITemplate
)                                           : IServiceServerAPI
{
    /****************************************************************************************************
     * Запрос в API сервера списка дисциплин, которые должен проходить студент с учётной записью        *
     * [userId].                                                                                        *
     * @param userId - идентификатор учётной записи пользователя, для которой необходимо получить       *
     * список дисциплин.                                                                                *
     * @return объект для отслеживания статуса запроса.                                                 *
     ***************************************************************************************************/
    override fun fetchSubjects(
        userId                              : UUID
    )                                       : Single<List<CSubject>>
    {
        return template.getSubjectsByUserId(userId.toString())
            //Выполняем операцию в фоновом потоке, чтобы не блокировать интерфейс.
            .subscribeOn(Schedulers.io())
    }


}