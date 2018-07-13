package com.cameronlay.comp3617.termproject.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.cameronlay.comp3617.termproject.model.*


@Database(entities = arrayOf(Artwork::class,
        CoffeeShop::class,
        CreativeIndustries::class,
        CulturalVenues::class,
        HeritageInterests::class,
        LanguageLiteracy::class,
        Playground::class,
        Recreation::class,
        Shopping::class,
        SportsFields::class), version = 1)
abstract class ProjectDatabase : RoomDatabase() {
    abstract fun artworkDAO(): ArtworkDAO
    abstract fun coffeeShopDAO(): CoffeeShopDAO
    abstract fun creativeIndustriesDAO(): CreativeIndustriesDAO
    abstract fun culturalVenturesDAO(): CulturalVenuesDAO
    abstract fun heritageInterestsDAO(): HeritageInterestsDAO
    abstract fun languageLiteracyDAO(): LanguageLiteracyDAO
    abstract fun playgroundDAO(): PlaygroundDAO
    abstract fun recreationDAO(): RecreationDAO
    abstract fun shoppingDAO(): ShoppingDAO
    abstract fun sportsFieldsDAO(): SportsFieldsDAO

    companion object {
        private var instance: ProjectDatabase? = null

        fun getInstance(context: Context): ProjectDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        ProjectDatabase::class.java,
                        "Database").build()
            }

            return instance as ProjectDatabase
        }
    }
}