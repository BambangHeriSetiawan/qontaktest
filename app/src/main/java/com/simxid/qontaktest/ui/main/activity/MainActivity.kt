package com.simxid.qontaktest.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.simxid.qontaktest.R
import com.simxid.qontaktest.ui.main.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.nav_search-> {
            }
            R.id.nav_filter-> {
            }
            R.id.nav_fav-> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun loadMainFragment(type:String){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,
            MainFragment.instance(type),"main")
    }
}
