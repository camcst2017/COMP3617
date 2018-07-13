package com.cameronlay.comp3617.termproject.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity (tableName = "shopping_table")
data class Shopping (
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var name: String = "",
        var description: String = "",
        var latitude: Double = 0.0,
        var longitude: Double = 0.0
)