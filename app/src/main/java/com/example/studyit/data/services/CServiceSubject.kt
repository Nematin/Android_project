package com.example.studyit.data.services

import android.content.Context
import com.example.studyit.data.dao.IDAOSubject
import com.example.studyit.model.CSubject
import com.example.studyit.utils.api.IServiceServerAPI

import java.util.*
import javax.inject.Inject

//import ru.psu.mobileapp.data.dao.IDAOSubject
import javax.inject.Singleton


/********************************************************************************************************
 * Класс реализует все методы бизнес-логики, которые могут применятся к объектам типа [CSubject].       *
 * @author Селетков И.П. 2019 0211.                                                                     *
 *******************************************************************************************************/
@Singleton
class CServiceSubject
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
    CServiceBase<CSubject, UUID>(daoSubject),
    IServiceSubject
{
//    /****************************************************************************************************
//     * Возвращает список всех имеющихся значений атрибута с идентификатором [attributeId] по            *
//     * контрольной точке [checkPointId].                                                                *
//     *                                                                                                  *
//     * @param checkPointId - идентификатор объекта.                                                     *
//     * @param attributeId - идентификатор атрибута.                                                     *
//     * @return - список значений атрибута.                                                              *
//     ***************************************************************************************************/
//    override fun get(
//        checkPointId: UUID,
//        attributeId: UUID
//    )                                       : Single<List<CAttributeWithValue>>
//    {
//        return daoAttributeValue.get(checkPointId, attributeId)
//    }
}