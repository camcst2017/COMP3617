package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface PlaygroundDAO {

    @Query ("SELECT * FROM playground_table")
    fun getPlayground(): List<Playground>

    @Query("SELECT * FROM playground_table WHERE id = :playgroundId")
    fun loadPlayground(playgroundId: Long): Playground

    @Query("DELETE FROM playground_table")
    fun nukeTable()

    @Insert(onConflict = REPLACE)
    fun insertPlayground(playground: Playground): Long

    @Update(onConflict = REPLACE)
    fun updatePlayground(playground: Playground)

    @Delete
    fun deletePlayground(playground: Playground)
}