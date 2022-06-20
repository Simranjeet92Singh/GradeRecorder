package com.gradeRecorder.graderecorder

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.gradeRecorder.graderecorder.Database.Database
import com.gradeRecorder.graderecorder.recyclerview.model
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
//    private var fragmentManager: FragmentManager?=null
//    private var fragmentTransaction: FragmentTransaction?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grey));
        }

        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

//        fragmentManager=supportFragmentManager
//        fragmentTransaction=fragmentManager?.beginTransaction()


//        fragmentTransaction?.replace(R.id.fcontinor, CourceList.newInstance())?.commit()

        supportFragmentManager?.beginTransaction()?.replace(R.id.fcontinor,CourceList())?.commit()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.settings -> {

               supportFragmentManager.beginTransaction().replace(R.id.fcontinor, Settings())?.addToBackStack(null)?.commit()

           }

           R.id.deleteAll ->{
               val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this@MainActivity)

               builder.setMessage("Are you sure , you want to delete All record?")
               builder.setTitle("Alert !")
               builder.setCancelable(false)
               builder
                   .setPositiveButton(
                       "Yes"
                   ) { dialog, which ->

                       GlobalScope.launch {
                           val databaseDAO = Database?.getInstance(this@MainActivity.applicationContext).modelDAO()
                            val model= model()
                           databaseDAO?.deleteAll()
                           supportFragmentManager?.beginTransaction()?.replace(R.id.fcontinor,CourceList())?.commit()

                       }

                       Toast.makeText(this@MainActivity,"All Record Deleted",Toast.LENGTH_SHORT).show()

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
        return true
    }


}