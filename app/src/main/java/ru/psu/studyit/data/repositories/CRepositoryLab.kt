package ru.psu.studyit.data.repositories

import android.content.Context
import ru.psu.studyit.data.dao.IDAOLab
import ru.psu.studyit.model.CLab
import ru.psu.studyit.model.CSubject
import ru.psu.studyit.utils.api.IServiceServerAPI
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/********************************************************************************************************
 * Класс реализует все методы бизнес-логики, которые могут применятся к объектам типа [CSubject].       *
 * @author Селетков И.П. Балышев А.М. 2019 0321.                                                                     *
 *******************************************************************************************************/
@Singleton
class CRepositoryLab
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
    CRepositoryBase<CLab, UUID>(daoLab),
    IRepositoryLab
{
}