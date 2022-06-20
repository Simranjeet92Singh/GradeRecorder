@file:Suppress("AndroidUnresolvedRoomSqlReference")

package com.gradeRecorder.graderecorder.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gradeRecorder.graderecorder.recyclerview.model

@Dao
interface DatabaseDAO {
    @Insert
   fun addData(model: model?)

    @Query("select * from `model`")
  fun getAllData(): List<model>

    @Query("select * from `model` where `key`=:key")
     fun getData(key:Int):model

    @Delete
    fun delete(model: model?)

    @Query("delete from `model`")
     fun deleteAll()
}