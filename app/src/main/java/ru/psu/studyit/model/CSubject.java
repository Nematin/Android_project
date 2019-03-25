//@author Баландин Артём
package ru.psu.studyit.model;

import androidx.room.Entity;

@Entity(
        tableName                               = "subjects" //,
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
)
public class CSubject extends CNamedObject{

}
