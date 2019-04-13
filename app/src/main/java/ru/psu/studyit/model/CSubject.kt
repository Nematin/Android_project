package ru.psu.studyit.model

import androidx.room.Entity

/********************************************************************************************************
 * Класс описывает научную дисциплину.                                                                  *
 * @author Селетков И.П. 2019 0331.                                                                     *
 *******************************************************************************************************/
@Entity(tableName                           = "subjects")//,
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
class CSubject                              : CNamedObject()
