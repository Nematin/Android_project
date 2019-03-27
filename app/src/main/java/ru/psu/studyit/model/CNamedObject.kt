//@author Баландин Артём
package ru.psu.studyit.model

import com.squareup.moshi.Json

import androidx.room.ColumnInfo

abstract class CNamedObject : CBaseObject() {

    /****************************************************************************************************
     * Наименование.                                                                                    *
     */

    @Json(name = "name")
    @ColumnInfo(name = "name")
    var name: String? = null


}
