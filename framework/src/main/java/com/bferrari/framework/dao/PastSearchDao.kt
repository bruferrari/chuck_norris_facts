package com.bferrari.framework.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bferrari.framework.model.PastSearchEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PastSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(query: PastSearchEntity): Completable

    @Query("SELECT * from past_searches order by date")
    fun read(): Flowable<List<PastSearchEntity>>
}