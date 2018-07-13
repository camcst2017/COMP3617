package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.cameronlay.comp3617.termproject.model.*


@Dao
interface CoffeeShopDAO {

    @Query ("SELECT * FROM coffeeshop_table")
    fun getCoffeeShops(): List<CoffeeShop>

    @Query("SELECT * FROM coffeeshop_table WHERE id = :coffeeshopId")
    fun loadCoffeeShop(coffeeshopId: Long): CoffeeShop

    @Query("DELETE FROM coffeeshop_table")
    fun nukeTable()

    @Insert(onConflict = REPLACE)
    fun insertCoffeeShop(coffeeShop: CoffeeShop): Long

    @Update(onConflict = REPLACE)
    fun updateCoffeeShop(coffeeShop: CoffeeShop)

    @Delete
    fun deleteCoffeeShop(coffeeShop: CoffeeShop)
}