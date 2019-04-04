package com.bferrari.framework.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bferrari.framework.model.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: List<CategoryEntity>): Completable

    @Query("SELECT * from categories order by RANDOM() LIMIT 8")
    fun read(): Flowable<List<CategoryEntity>>
}