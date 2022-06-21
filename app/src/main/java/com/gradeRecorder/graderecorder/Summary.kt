package com.gradeRecorder.graderecorder

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gradeRecorder.graderecorder.Database.Database
import com.gradeRecorder.graderecorder.Database.DatabaseDAO
import com.gradeRecorder.graderecorder.recyclerview.Adapter
import com.gradeRecorder.graderecorder.recyclerview.SummaryAdapter
import com.gradeRecorder.graderecorder.recyclerview.SummaryModel
import com.gradeRecorder.graderecorder.recyclerview.model
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Summary: Fragment(){
    var summaryModel:ArrayList<SummaryModel>?=ArrayList()
    var model:ArrayList<model>?= ArrayList()
    private var databaseDAO: DatabaseDAO?=null

    private  var recycler: RecyclerView?=null
    private  var floatingButton: FloatingActionButton?=null
    var x : Double = 0.0
    var y :Double =0.0
    var p : Double = 0.0
    var q :Double =0.0


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


}
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.summary, container, false)


}

@RequiresApi(Build.VERSION_CODES.P)
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val ac = activity as AppCompatActivity
    ac ?: return


    recycler = view?.findViewById(R.id.recycler_summary)




    recycler?.layoutManager = LinearLayoutManager(ac.applicationContext)
    recycler?.setHasFixedSize(false)

    GlobalScope.launch {
        databaseDAO = Database?.getInstance(ac.applicationContext).modelDAO()
        model = databaseDAO!!.getAllData() as ArrayList<model>

    }
Log.d("getCourseName",getcourseName(model!!).toString())

    for(  j in 0..getcourseName(model!!)?.size!!-1){
            summaryModel?.add(SummaryModel(getcourseName(model!!)?.get(j).toString(),getgradesSubmitted(model!!).get(j)
                ,getgradestoDate(model!!).get(j)))


            }


    MainScope().launch {
        val adapter = SummaryAdapter(ac, summaryModel)

        recycler?.adapter = adapter

    }
}
    companion object {

        @JvmStatic
        fun newInstance() =
            Summary()
    }

    fun getcourseName(mlist:ArrayList<model>):List<String>?{

        var newList:ArrayList<String>?=ArrayList()
        var newListOne:List<String>?=ArrayList()
        for(position in 0..mlist.size-1){
            newList?.add(mlist.get(position).courseName.toString())
        }
        newListOne= newList?.distinct()
        Log.d("New list",newListOne.toString())
        return newListOne

    }

    fun getgradesSubmitted( mlist:ArrayList<model>):ArrayList<Double>{

        var n : ArrayList<Double> = ArrayList()


        for(  j in 0..getcourseName(mlist)?.size!!-1){

            for(i in 0..mlist.size-1){
                if(mlist.get(i).courseName == getcourseName(mlist)?.get(j).toString()){
                p=p+mlist.get(i).gradesReceived!!
                        q=q+mlist.get(i).totalGrades!!

                }else{
                    p=p+0
                    q=q+0
                }

            }
            n.add((p/q)*100)
        }

       return n
    }

    fun getgradestoDate(mlist:ArrayList<model>):ArrayList<Double>{
        var n : ArrayList<Double> = ArrayList()
        for(  j in 0..getcourseName(mlist)?.size!!-1) {
            for (i in 0..mlist.size - 1) {
                x = x + mlist.get(i).gradesReceived!!
                y = y + mlist.get(i).totalGrades!!
            }
            n.add((x/y)*100)
        }


        return n
    }

}