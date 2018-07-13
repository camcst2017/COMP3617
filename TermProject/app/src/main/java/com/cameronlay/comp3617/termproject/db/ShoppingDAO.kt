package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface ShoppingDAO {

    @Query ("SELECT * FROM shopping_table")
    fun getShopping(): List<Shopping>

    @Query("SELECT * FROM shopping_table WHERE id = :shoppingId")
    fun loadShopping(shoppingId: Long): Shopping

    @Insert(onConflict = REPLACE)
    fun insertShopping(shopping: Shopping): Long

    @Update(onConflict = REPLACE)
    fun updateShopping(shopping: Shopping)

    @Delete
    fun deleteShopping(shopping: Shopping)
}