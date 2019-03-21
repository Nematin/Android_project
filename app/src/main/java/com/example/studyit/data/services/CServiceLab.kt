package com.example.studyit.data.services

import android.content.Context
import com.example.studyit.data.dao.IDAOLab
import com.example.studyit.data.dao.IDAOSubject
import com.example.studyit.model.CLab
import com.example.studyit.model.CSubject
import com.example.studyit.utils.api.IServiceServerAPI
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/********************************************************************************************************
 * Класс реализует все методы бизнес-логики, которые могут применятся к объектам типа [CSubject].       *
 * @author Селетков И.П. 2019 0211.                                                                     *
 *******************************************************************************************************/
@Singleton
class CServiceLab
/********************************************************************************************************
 * Конструктор.                                                                                         *
 *******************************************************************************************************/
@Inject
constructor
(
        private val context                     : Context,
        private val daoLab                      : IDAOLab,
        private val serviceServerAPI            : IServiceServerAPI

)                                               :
        CServiceBase<CLab, UUID>(daoLab),
        IServiceLab
{
}