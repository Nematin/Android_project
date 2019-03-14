package com.example.studyit.model;

import com.squareup.moshi.Json;

import androidx.room.ColumnInfo;

public abstract class CNamedObject extends CBaseObject{

    /****************************************************************************************************
     * Наименование.                                                                                    *
     ***************************************************************************************************/
    @Json(name                              = "name")
    @ColumnInfo(name                        = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
