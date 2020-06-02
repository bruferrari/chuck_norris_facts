package com.bferrari.framework.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
data class FactEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "value")
    var value: String?
)