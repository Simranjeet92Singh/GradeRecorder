package com.gradeRecorder.graderecorder

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private var fragmentManager: FragmentManager?=null
    private var fragmentTransaction: FragmentTransaction?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grey));
        }

        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        fragmentManager=supportFragmentManager
        fragmentTransaction=fragmentManager?.beginTransaction()

        var addcourseFragment:Addcourse = Addcourse()
        fragmentTransaction?.replace(android.R.id.content, addcourseFragment)?.commit()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.settings -> {
               fragmentManager=supportFragmentManager
               fragmentTransaction=fragmentManager?.beginTransaction()

               var settings= Settings()
               fragmentTransaction?.replace(android.R.id.content, settings)?.commit()

           }
       }
        return true
    }
}