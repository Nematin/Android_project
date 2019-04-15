package ru.psu.studyit.data.repositories

import android.content.Context
import android.util.Log
import io.reactivex.Flowable
import ru.psu.studyit.data.CNetworkBoundResource
import ru.psu.studyit.data.CResource
import ru.psu.studyit.data.dao.IDAOSubject
import ru.psu.studyit.model.CSubject
import ru.psu.studyit.utils.api.IServiceServerAPI

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/********************************************************************************************************
 * Класс реализует все методы бизнес-логики, которые могут применятся к объектам типа [CSubject].       *
 * @author Селетков И.П. 2019 0211.                                                                     *
 *******************************************************************************************************/
@Singleton
class CRepositorySubject
/********************************************************************************************************
 * Конструктор.                                                                                         *
 *******************************************************************************************************/
@Inject
constructor
(
        private val context                     : Context,
        private val daoSubject                  : IDAOSubject,
        private val serviceServerAPI            : IServiceServerAPI

)                                           :
    CRepositoryBase<CSubject, UUID>(daoSubject),
    IRepositorySubject
{
    /****************************************************************************************************
     * Делает запрос на сервер, сохраняет в БД, возвращает список полученных дисциплин,                 *
     * которые должен проходить пользователь с текущей учётной записью.                                 *
     * @return - список дисциплин и статус.                                                             *
     ***************************************************************************************************/
    override fun getResource(
    )                                       : Flowable<CResource<List<CSubject>>>
    {
        return object                       : CNetworkBoundResource<List<CSubject>, List<CSubject>>() {

            override fun saveCallResult(
                item                        : List<CSubject>)
            {
                daoSubject.insert(item)
            }

            override fun shouldFetch(
            )                               : Boolean
            {
                return true
            }

            override fun loadFromDB(
            )                               : Flowable<List<CSubject>>
            {
                return daoSubject.getFlowable()
            }

            override fun createCall(

            )                               : Flowable<CResource<List<CSubject>>>
            {
                return serviceServerAPI.fetchSubjects()
                    .flatMapPublisher {
                        list ->
                        Flowable.just(
                            CResource.success(list)
                        )
                    }
            }
        }
            .flowable
            .doOnError {
                Log.e("STUDYIT", "Error while fetching subjects from server", it)
            }
    }
}