package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface ArtworkDAO {

    @Query ("SELECT * FROM artwork_table")
    fun getArtwork(): List<Artwork>

    @Query("SELECT * FROM artwork_table WHERE id = :artworkId")
    fun loadArtwork(artworkId: Long): Artwork

    @Query("DELETE FROM artwork_table")
    fun nukeTable()

    @Insert(onConflict = REPLACE)
    fun insertArtwork(artwork: Artwork): Long

    @Update(onConflict = REPLACE)
    fun updateArtwork(artwork: Artwork)

    @Delete
    fun deleteArtwork(artwork: Artwork)
}