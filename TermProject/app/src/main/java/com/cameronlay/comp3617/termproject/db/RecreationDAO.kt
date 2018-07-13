package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface RecreationDAO {

    @Query ("SELECT * FROM recreation_table")
    fun getRecreation(): List<Recreation>

    @Query("SELECT * FROM recreation_table WHERE id = :recreationId")
    fun loadRecreation(recreationId: Long): Recreation

    @Query("DELETE FROM recreation_table")
    fun nukeTable()

    @Insert(onConflict = REPLACE)
    fun insertRecreation(recreation: Recreation): Long

    @Update(onConflict = REPLACE)
    fun updateRecreation(recreation: Recreation)

    @Delete
    fun deleteRecreation(recreation: Recreation)
}