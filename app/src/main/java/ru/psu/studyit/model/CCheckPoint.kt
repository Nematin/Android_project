package ru.psu.studyit.model

import androidx.room.Entity

/********************************************************************************************************
 * Класс описывает контрольную точку .                                                                  *
 * @author Плюснин и Ко. 2019 0429.                                                                     *
 *******************************************************************************************************/
@Entity(tableName                           = "checkpoints")//,
//  Пример описания внешнего ключа.
//    indices                                 = [
//        Index("creator_id"),
//    ],
//    foreignKeys                             = [
//        ForeignKey(
//            entity                          = CUser::class,
//            parentColumns                   = ["id"],
//            childColumns                    = ["creator_id"]
//        ),
//    ]
class CCheckPoint                              : CNamedObject()