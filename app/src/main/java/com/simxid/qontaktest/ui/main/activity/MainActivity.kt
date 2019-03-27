package com.simxid.qontaktest.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.ActivityMainBinding
import com.simxid.qontaktest.helper.Helper
import com.simxid.qontaktest.ui.main.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var vm:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        vm = MainActivityViewModel()
        binding.lifecycleOwner = this
        binding.mainVm = vm
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        loadMainFragment(Helper.TYPE_POP,Helper.TYPE_POP)
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

    private fun loadMainFragment(type:String, tag:String){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,
            MainFragment.instance(type),tag)
    }
}
