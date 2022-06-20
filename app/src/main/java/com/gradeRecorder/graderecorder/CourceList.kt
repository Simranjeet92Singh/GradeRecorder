package com.gradeRecorder.graderecorder

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gradeRecorder.graderecorder.Addcourse
import com.gradeRecorder.graderecorder.Database.Database
import com.gradeRecorder.graderecorder.Database.DatabaseDAO
import com.gradeRecorder.graderecorder.R
import com.gradeRecorder.graderecorder.recyclerview.Adapter
import com.gradeRecorder.graderecorder.recyclerview.model
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CourceList : Fragment() {

     var model:ArrayList<model>?= ArrayList()
    private var databaseDAO: DatabaseDAO?=null

  private  var recycler:RecyclerView?=null
  private  var floatingButton:FloatingActionButton?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_course_list, container, false)






    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ac = activity as AppCompatActivity
        ac ?: return
        recycler=view?.findViewById(R.id.recycler)
        floatingButton=view?.findViewById<FloatingActionButton>(R.id.ac)
        val firstText=view?.findViewById<TextView>(R.id.frontText)

//        firstText.visibility=View.GONE

        floatingButton?.setOnClickListener{



           activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fcontinor,Addcourse())?.addToBackStack(null)?.commit()

        }

       recycler?.layoutManager= LinearLayoutManager(ac.applicationContext)
        recycler?.setHasFixedSize(true)

        GlobalScope.launch {
            databaseDAO = Database?.getInstance(ac.applicationContext).modelDAO()
            model = databaseDAO?.getAllData() as ArrayList<model>

        }


        MainScope().launch {
            val  adapter= Adapter(ac,fragmentManager, model!!)
            adapter.notifyDataSetChanged()
            recycler?.adapter=adapter

            if(model?.size==0){
                Log.d("List size ","${model?.size}")
//            firstText.visibility=View.VISIBLE
                firstText.text="Hello! Welcome to Grade Recorder, Press Add button to begin."
            }else{
//            firstText.visibility=View.GONE
            }

        }




    }
    companion object {

        @JvmStatic
        fun newInstance() =
            CourceList()
    }
}