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
    public String _id;
    public String _createdAt;
    public String _updatedAt;
    public boolean _active;
    public String _name;
    public String _description;
}
