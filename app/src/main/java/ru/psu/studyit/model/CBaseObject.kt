//@author Баландин Артём
package ru.psu.studyit.model

import com.squareup.moshi.Json

import java.util.UUID
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

abstract class CBaseObject {

    /****************************************************************************************************
     * Идентификатор.                                                                                   *
     */
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    @ColumnInfo(name = "id")
    var id: UUID = UUID.randomUUID()
}
