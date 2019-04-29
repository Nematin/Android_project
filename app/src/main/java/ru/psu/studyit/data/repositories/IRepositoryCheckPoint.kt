package ru.psu.studyit.data.repositories

import io.reactivex.Flowable
import ru.psu.studyit.data.CResource
import ru.psu.studyit.model.CCheckPoint
import ru.psu.studyit.model.CSubject
import java.util.*

/********************************************************************************************************
 * Интерфейс описывает все методы бизнес-логики, которые могут применятся к объектам типа               *
 * [CCheckPoint].                                                                                       *
 * @author  Плюснин и Ко. 2019 0429.                                                                    *
 *******************************************************************************************************/
interface IRepositoryCheckPoint               : IRepositoryBase<CCheckPoint, UUID>
{
    /****************************************************************************************************
     * Делает запрос на сервер, сохраняет в БД, возвращает список полученных кт,                 *
     * для выбранной дисциплины.                                                                 *
     * @return - список кт и статус.                                                             *
     ***************************************************************************************************/
    fun getResource(
    )                                       : Flowable<CResource<List<CCheckPoint>>>
}