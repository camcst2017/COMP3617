package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface HeritageInterestsDAO {

    @Query ("SELECT * FROM heritageinterests_table")
    fun getHeritageInterests(): List<HeritageInterests>

    @Query("SELECT * FROM heritageinterests_table WHERE id = :heritageinterestId")
    fun loadHeritageInterest(heritageinterestId: Long): HeritageInterests

    @Query("DELETE FROM heritageinterests_table")
    fun nukeTable()

    @Insert(onConflict = REPLACE)
    fun insertHeritageInterest(heritageInterests: HeritageInterests): Long

    @Update(onConflict = REPLACE)
    fun updateHeritageInterest(heritageInterests: HeritageInterests)

    @Delete
    fun deleteHeritageInterest(heritageInterests: HeritageInterests)
}