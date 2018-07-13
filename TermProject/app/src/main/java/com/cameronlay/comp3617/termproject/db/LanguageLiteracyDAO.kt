package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface LanguageLiteracyDAO {

    @Query ("SELECT * FROM languageliteracy_table")
    fun getLanguageLiteracy(): List<LanguageLiteracy>

    @Query("SELECT * FROM languageliteracy_table WHERE id = :languageliteracyId")
    fun loadLanguageLiteracy(languageliteracyId: Long): LanguageLiteracy
    @Query("DELETE FROM languageliteracy_table")
    fun nukeTable()


    @Insert(onConflict = REPLACE)
    fun insertLanguageLiteracy(languageLiteracy: LanguageLiteracy): Long

    @Update(onConflict = REPLACE)
    fun updateLanguageLiteracy(languageLiteracy: LanguageLiteracy)

    @Delete
    fun deleteLanguageLiteracy(languageLiteracy: LanguageLiteracy)
}