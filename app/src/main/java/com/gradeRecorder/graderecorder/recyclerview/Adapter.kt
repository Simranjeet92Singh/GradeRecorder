package com.gradeRecorder.graderecorder.recyclerview

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gradeRecorder.graderecorder.CourceList
import com.gradeRecorder.graderecorder.Database.Database
import com.gradeRecorder.graderecorder.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Adapter (val context:Context,val fragmentManager: FragmentManager?, val list:ArrayList<model>) :RecyclerView.Adapter<Adapter.ItemViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_view,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val model=list.get(position)


            holder.courseName?.text=model.courseName
            holder.courseType?.text="Type of Grade : ${model.typeOfGrade}"
            holder.totalGrade?.text="Percentage worth of total grade: ${model.totalGrades}"
            holder.obtGrades?.text="Grade received: ${model.gradesReceived}"





        holder.itemView.setOnClickListener {

            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)

            builder.setMessage("Are you sure , you want to delete the record?")
            builder.setTitle("Alert !")
            builder.setCancelable(false)
            builder
                .setPositiveButton(
                    "Yes"
                ) { dialog, which ->

                    GlobalScope.launch {
                        val databaseDAO = Database?.getInstance(context.applicationContext).modelDAO()

                        databaseDAO?.delete(model)
                        fragmentManager?.beginTransaction()?.replace(R.id.fcontinor,
                            CourceList()
                        )?.commit()

                    }
                    Toast.makeText(context.applicationContext," Record Deleted",Toast.LENGTH_SHORT).show()


                }

            builder
                .setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener { dialog, which ->

                        dialog.cancel()
                    })

            val alertDialog = builder.create()


            alertDialog.show()
        }
    }



    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        val courseName: TextView?=view?.findViewById(R.id.CourseNumber)
        val courseType: TextView?=view?.findViewById(R.id.gradeType)
        val totalGrade: TextView?=view?.findViewById(R.id.totalGrade)
        val obtGrades: TextView?=view?.findViewById(R.id.GradesRecieved)

    }


}