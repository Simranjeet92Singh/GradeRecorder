package com.gradeRecorder.graderecorder

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gradeRecorder.graderecorder.Database.Database
import com.gradeRecorder.graderecorder.Database.DatabaseDAO
import com.gradeRecorder.graderecorder.recyclerview.model
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Addcourse: Fragment() {
   private var courseName: EditText?=null
    private var totalGrades:EditText?=null
    private var obtainGrades:EditText?=null
    private var gradeType:EditText?=null
    private var addButton: Button?=null
//    private var backButton:Button?=null
    private var model: model?=null
    private var dataBase: Database?=null
    private var databaseDAO:DatabaseDAO?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_addcource, container, false)


    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ac = activity as AppCompatActivity
        ac ?: return
        model=model()
        courseName=view?.findViewById(R.id.CourseName)
        totalGrades=view?.findViewById(R.id.totalGrade)
        obtainGrades=view?.findViewById(R.id.GradesRecieved)
        gradeType=view?.findViewById(R.id.gradeType)
        addButton=view?.findViewById(R.id.buttonNextNewAcivity)
//        backButton=view?.findViewById(R.id.buttonBack)


addButton?.setOnClickListener({

    if(courseName?.text.toString()==""){
        Toast.makeText(context,"Course Number is empty.", Toast.LENGTH_SHORT).show()
    } else if(gradeType?.text.toString()==""){
        Toast.makeText(context,"Grade Type is empty.", Toast.LENGTH_SHORT).show()

    }else if(totalGrades?.text.toString()==""){
        Toast.makeText(context,"Total Grades is empty.", Toast.LENGTH_SHORT).show()

    }else if(obtainGrades?.text.toString()==""){
        Toast.makeText(context,"Grades Recieved is empty.", Toast.LENGTH_SHORT).show()

    }else {

        GlobalScope.launch {

            model?.key = 0
            model?.courseName = courseName?.text.toString()
            model?.totalGrades = totalGrades?.text.toString()
            model?.gradesReceived = obtainGrades?.text.toString()
            model?.typeOfGrade = gradeType?.text.toString()

            databaseDAO = Database?.getInstance(ac.applicationContext).modelDAO()

            databaseDAO?.addData(model)

        }

        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fcontinor,CourceList())?.commit()
        activity?.supportFragmentManager?.popBackStack()
    }

})

//        backButton?.setOnClickListener({
//            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fcontinor,CourceList())?.commit()
//
//
//
//        })
    }

}