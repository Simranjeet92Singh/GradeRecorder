package com.gradeRecorder.graderecorder.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gradeRecorder.graderecorder.CourceList
import com.gradeRecorder.graderecorder.Database.Database
import com.gradeRecorder.graderecorder.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SummaryAdapter (val context: Context, val list:List<SummaryModel>?) :
    RecyclerView.Adapter<SummaryAdapter.ItemViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryAdapter.ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.summary_list,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val model=list?.get(position)


        holder.tv_name?.text= model?.cName.toString()
       holder.gradesToDate?.text="${model?.gradesSubmitted.toString()}% grades submitted"
        holder.gradesSubmitted?.text="${model?.gradesToDate.toString()}% grades to Date"


    }



    override fun getItemCount(): Int {
        return list!!.size
    }

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_name: TextView?=view?.findViewById(R.id.tv_name)
        val gradesToDate: TextView?=view?.findViewById(R.id.gradeTodate)
        val gradesSubmitted: TextView?=view?.findViewById(R.id.gradesSubmitted)


    }


}