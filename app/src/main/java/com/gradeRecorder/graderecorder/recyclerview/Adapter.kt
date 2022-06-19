package com.gradeRecorder.graderecorder.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gradeRecorder.graderecorder.R

class Adapter (val context:Context,val fragmentManager: FragmentManager?, val list:ArrayList<model>) :RecyclerView.Adapter<Adapter.ItemViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_view,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val model=list.get(position)
        holder.courseName?.text=model.courseName
        holder.courseType?.text=model.typeOfGrade
        holder.totalGrade?.text=model.totalGrades
        holder.obtGrades?.text=model.gradesReceived


    }



    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        val courseName: TextView?=view?.findViewById(R.id.CourseName)
        val courseType: TextView?=view?.findViewById(R.id.gradeType)
        val totalGrade: TextView?=view?.findViewById(R.id.totalGrade)
        val obtGrades: TextView?=view?.findViewById(R.id.GradesRecieved)

    }


}