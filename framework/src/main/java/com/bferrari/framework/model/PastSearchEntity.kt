package com.bferrari.framework.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "past_searches")
data class PastSearchEntity(
    @PrimaryKey
    @ColumnInfo(name = "query")
    var query: String,
    @ColumnInfo(name = "date")
    var date: Date)