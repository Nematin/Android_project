package ru.psu.studyit.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity(primaryKeys = ["id"])
data class SubjectEntity(
        @SerializedName("id")
        val id: UUID,

        @SerializedName(value = "createdAt")
        val createdAt: String,

        @SerializedName("updatedAt")
        val updatedAt: String,

        @SerializedName(value = "active")
        var active: Boolean,

        @SerializedName(value = "name")
        val name: String,

        @SerializedName(value = "description")
        val description: String
) : Parcelable {

    constructor(source: Parcel) : this(
            UUID.fromString(source.readString()),
            source.readString(),
            source.readString(),
            source.readString().toBoolean(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id.toString())
        writeString(createdAt)
        writeString(updatedAt)
        writeString(active.toString())
        writeString(name)
        writeString(description)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SubjectEntity> = object : Parcelable.Creator<SubjectEntity> {
            override fun createFromParcel(source: Parcel): SubjectEntity = SubjectEntity(source)
            override fun newArray(size: Int): Array<SubjectEntity?> = arrayOfNulls(size)
        }
    }
}