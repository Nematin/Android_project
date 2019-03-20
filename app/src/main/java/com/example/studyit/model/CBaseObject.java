//@author Баландин Артём
package com.example.studyit.model;

import com.squareup.moshi.Json;

import java.util.UUID;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public abstract class CBaseObject {

    /****************************************************************************************************
     * Идентификатор.                                                                                   *
     ***************************************************************************************************/
    @PrimaryKey(autoGenerate                = false)
    @Json(name                              = "id")
    @ColumnInfo(name                        = "id")
    private UUID id;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
