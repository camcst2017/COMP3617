package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface SportsFieldsDAO {

    @Query("SELECT * FROM sportsfields_table")
    fun getSportsFields(): List<SportsFields>

    @Query("SELECT * FROM sportsfields_table WHERE id = :sportsFieldsId")
    fun loadSportsFields(sportsFieldsId: Long): SportsFields

    @Query("DELETE FROM sportsfields_table")
    fun nukeTable()

    @Insert(onConflict = REPLACE)
    fun insertSportsFields(sportsField: SportsFields): Long

    @Update(onConflict = REPLACE)
    fun updateSportsFields(sportsField: SportsFields)

    @Delete
    fun deleteSportsFields(sportsField: SportsFields)
}