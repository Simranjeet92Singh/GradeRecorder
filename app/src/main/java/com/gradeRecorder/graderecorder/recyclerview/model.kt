package com.gradeRecorder.graderecorder.recyclerview

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="model")
data class model (
    @PrimaryKey(autoGenerate = true)
     var key:Int=0,
     var courseName:String?="null",
     var totalGrades:Double?=0.0,
     var gradesReceived:Double?=0.0,
    var typeOfGrade:String?="null",

        ):Serializable{
}