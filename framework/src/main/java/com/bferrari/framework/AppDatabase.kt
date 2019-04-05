package com.bferrari.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bferrari.framework.dao.CategoryDao
import com.bferrari.framework.model.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}