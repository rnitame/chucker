package com.chuckerteam.chucker.api.internal.data.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.chuckerteam.chucker.api.internal.data.entity.RecordedThrowable
import com.chuckerteam.chucker.api.internal.data.entity.RecordedThrowableTuple

@Dao
internal interface RecordedThrowableDao {

    @Query("SELECT id,tag,date,clazz,message FROM throwables ORDER BY date DESC")
    fun getTuples(): LiveData<List<RecordedThrowableTuple>>

    @Insert()
    fun insert(throwable: RecordedThrowable): Long?

    @Query("DELETE FROM throwables")
    fun deleteAll()

    @Query("SELECT * FROM throwables WHERE id = :id")
    fun getById(id: Long): LiveData<RecordedThrowable>

    @Query("DELETE FROM throwables WHERE date <= :threshold")
    fun deleteBefore(threshold: Long)
}
