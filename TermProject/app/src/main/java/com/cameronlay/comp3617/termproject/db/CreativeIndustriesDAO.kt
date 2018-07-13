package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface CreativeIndustriesDAO {

    @Query ("SELECT * FROM creativeindustries_table")
    fun getCreativeIndustries(): List<CreativeIndustries>

    @Query("SELECT * FROM creativeindustries_table WHERE id = :creativeindustriesId")
    fun loadCreativeIndustries(creativeindustriesId: Long): CreativeIndustries

    @Query("DELETE FROM creativeindustries_table")
    fun nukeTable()

    @Insert(onConflict = REPLACE)
    fun insertCreativeIndustries(creativeIndustries: CreativeIndustries): Long

    @Update(onConflict = REPLACE)
    fun updateCreativeIndustries(creativeIndustries: CreativeIndustries)

    @Delete
    fun deleteCreativeIndustries(creativeIndustries: CreativeIndustries)
}