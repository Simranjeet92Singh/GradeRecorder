package com.gradeRecorder.graderecorder.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gradeRecorder.graderecorder.recyclerview.model

@Dao
interface DatabaseDAO {
    @Insert
    suspend fun addData(model: model?)

    @Query("select * from `model`")
    suspend fun getAllData():List<model>

    @Query("select * from `model` where `key`=:key")
    suspend fun getData(key:Int):model

    @Delete
    suspend fun delete(model: model?)

    @Query("delete from `model`")
    suspend fun deleteAll()
}