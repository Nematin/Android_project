package ru.psu.studyit.utils.api

import ru.psu.studyit.model.CSubject
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.psu.studyit.model.CCheckPoint
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
     * Запрос в API сервера списка дисциплин, которые должен проходить студент с текущей учётной        *
     * записью.                                                                                         *
     * @return объект для отслеживания статуса запроса.                                                 *
     ***************************************************************************************************/
    override fun fetchSubjects(
    )                                       : Single<List<CSubject>>
    {
        return template.getSubjects()
            //Выполняем операцию в фоновом потоке, чтобы не блокировать интерфейс.
            .subscribeOn(Schedulers.io())
    }

    /****************************************************************************************************
     * Запрос в API сервера списка кт, которые должен проходить студент с текущей учётной               *
     * записью.                                                                                         *
     * @return объект для отслеживания статуса запроса.                                                 *
     ***************************************************************************************************/
    override fun fetchCheckPoints(
    )                                       : Single<List<CCheckPoint>>
    {
        return template.getCheckPoints()
                //Выполняем операцию в фоновом потоке, чтобы не блокировать интерфейс.
                .subscribeOn(Schedulers.io())
    }
}