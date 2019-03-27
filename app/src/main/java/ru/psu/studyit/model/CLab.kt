//@author Баландин Артём, Балышев
package ru.psu.studyit.model

import com.squareup.moshi.Json

import java.util.UUID

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Labs")//
class CLab(
        //   private String name;
        /****************************************************************************************************
         * Крайний срок.                                                                                    *
         */
        @Json(name = "deadline")
        @ColumnInfo(name = "deadline")
        var deadline: String? = null,
        /****************************************************************************************************
         * Крайний срок.                                                                                    *
         */
        @Json(name = "discipline")
        @ColumnInfo(name = "discipline")
        var discipline: String? = null,
        /****************************************************************************************************
        * Крайний срок.                                                                                    *
        */
        @Json(name = "control_point")
        @ColumnInfo(name = "control_point")
        var controlPoint: String? = null,


        var status: UUID? = null
) : CNamedObject() {



}
