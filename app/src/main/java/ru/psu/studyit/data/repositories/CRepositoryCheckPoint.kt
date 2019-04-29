package ru.psu.studyit.data.repositories

import android.content.Context
import android.util.Log
import io.reactivex.Flowable
import ru.psu.studyit.data.CNetworkBoundResource
import ru.psu.studyit.data.CResource
import ru.psu.studyit.data.dao.IDAOCheckPoint
import ru.psu.studyit.model.CCheckPoint
import ru.psu.studyit.utils.api.IServiceServerAPI

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/********************************************************************************************************
 * Класс реализует все методы бизнес-логики, которые могут применятся к объектам типа [CCheckPoint].    *
 * @author Плюснин и Ко. 2019 0429.                                                                     *
 *******************************************************************************************************/
@Singleton
class CRepositoryCheckPoint
/********************************************************************************************************
 * Конструктор.                                                                                         *
 *******************************************************************************************************/
@Inject
constructor
(
        private val context                     : Context,
        private val daoSubject                  : IDAOCheckPoint,
        private val serviceServerAPI            : IServiceServerAPI

)                                           :
        CRepositoryBase<CCheckPoint, UUID>(daoSubject),
        IRepositoryCheckPoint
{
    /****************************************************************************************************
     * Делает запрос на сервер, сохраняет в БД, возвращает список полученных кт,                        *
     * для выбранной дисциплины.                                                                        *
     * @return - список кт и статус.                                                                    *
     ***************************************************************************************************/
    override fun getResource(
    )                                       : Flowable<CResource<List<CCheckPoint>>>
    {
        return object                       : CNetworkBoundResource<List<CCheckPoint>, List<CCheckPoint>>() {

            override fun saveCallResult(
                    item                        : List<CCheckPoint>)
            {
                daoSubject.insert(item)
            }

            override fun shouldFetch(
            )                               : Boolean
            {
                return true
            }

            override fun loadFromDB(
            )                               : Flowable<List<CCheckPoint>>
            {
                return daoSubject.getFlowable()
            }

            override fun createCall(

            )                               : Flowable<CResource<List<CCheckPoint>>>
            {
                return serviceServerAPI.fetchCheckPoints()
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
                    Log.e("STUDYIT", "Error while fetching checkpoints from server", it)
                }
    }
}