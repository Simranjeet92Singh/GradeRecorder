package com.gradeRecorder.graderecorder.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gradeRecorder.graderecorder.recyclerview.model

@Database(entities = [model::class], version = 1, exportSchema = false)
abstract class Database:RoomDatabase() {
    abstract fun modelDAO():DatabaseDAO
    companion object{
private var instance:com.gradeRecorder.graderecorder.Database.Database?=null
        @Synchronized
        fun getInstance(ctx: Context):com.gradeRecorder.graderecorder.Database.Database{
            if (instance==null){
                instance= Room.databaseBuilder(ctx.applicationContext,com.gradeRecorder.graderecorder.Database.Database::class.java,"gradeRecorder_database").fallbackToDestructiveMigration().build()


            }
            return instance!!
        }
        

    }
}