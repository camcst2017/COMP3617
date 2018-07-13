package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface CulturalVenuesDAO {

    @Query ("SELECT * FROM culturalvenues_table")
    fun getCulturalVenues(): List<CulturalVenues>

    @Query("SELECT * FROM culturalvenues_table WHERE id = :culturalvenueId")
    fun loadCulturalVenue(culturalvenueId: Long): CulturalVenues

    @Query("DELETE FROM culturalvenues_table")
    fun nukeTable()

    @Insert(onConflict = REPLACE)
    fun insertCulturalVenue(culturalVenues: CulturalVenues): Long

    @Update(onConflict = REPLACE)
    fun updateCulturalVenue(culturalVenues: CulturalVenues)

    @Delete
    fun deleteCulturalVenue(culturalVenues: CulturalVenues)
}