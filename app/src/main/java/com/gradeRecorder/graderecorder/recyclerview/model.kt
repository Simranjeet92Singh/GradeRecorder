package com.gradeRecorder.graderecorder.recyclerview

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class model (
    @PrimaryKey(autoGenerate = true)
     var key:Int?=null,
     var courseName:String?=null,
     var totalGrades:String?=null,
     var gradesReceived:String?=null,
    var typeOfGrade:String?=null,

        ):Serializable{
}