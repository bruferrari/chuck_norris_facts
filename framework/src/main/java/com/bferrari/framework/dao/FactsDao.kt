package com.bferrari.framework.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bferrari.framework.model.FactEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(facts: List<FactEntity>): Completable

    @Query("SELECT * FROM facts ORDER BY RANDOM() LIMIT 1")
    fun read(): Flowable<FactEntity>

    @Query("DELETE FROM facts")
    fun clearTable()
}