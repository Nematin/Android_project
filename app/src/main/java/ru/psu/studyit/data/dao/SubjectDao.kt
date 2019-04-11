package ru.psu.studyit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.psu.studyit.data.entity.SubjectEntity

@Dao
interface SubjectDao {

    /* Method to insert the movies fetched from api
     * to room */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDisciplines(movies: List<SubjectEntity>): LongArray

    /* Method to fetch the movies stored locally */
    @Query("SELECT * FROM SubjectEntity")
    fun getDisciplines(): List<SubjectEntity>
}