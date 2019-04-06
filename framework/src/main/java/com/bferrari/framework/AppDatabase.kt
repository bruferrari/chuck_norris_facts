package com.bferrari.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bferrari.framework.converters.DateTypeConverter
import com.bferrari.framework.dao.CategoryDao
import com.bferrari.framework.dao.PastSearchDao
import com.bferrari.framework.model.CategoryEntity
import com.bferrari.framework.model.PastSearchEntity

@Database(entities = [CategoryEntity::class, PastSearchEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun pastSearchDao(): PastSearchDao
}