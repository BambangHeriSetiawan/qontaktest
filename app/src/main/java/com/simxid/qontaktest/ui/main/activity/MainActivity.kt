package com.simxid.qontaktest.ui.main.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.simxid.data.Const
import com.simxid.data.local.LocalRepo
import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.ActivityMainBinding
import com.simxid.qontaktest.helper.DialogHelper
import com.simxid.qontaktest.helper.Helper
import com.simxid.qontaktest.ui.fav.FavoriteActivity
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
        loadMainFragment(Const.type_pop,Const.type_pop)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.nav_search-> {
                LocalRepo.getPopMovies()?.onError {
                    Log.e("MainActivity","getPopMovies -> $it")
                }?.observer {
                    Log.v("MainActivity","getPopMovies -> $it")
                }
                LocalRepo.getTopMovies()?.onError {
                    Log.e("MainActivity","getTopMovies -> $it")
                }?.observer {
                    Log.v("MainActivity","getTopMovies -> $it")
                }
            }
            R.id.nav_filter-> {
                showDialogChose()
            }

            R.id.nav_fav-> {
                FavoriteActivity.start(this@MainActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialogChose() {
        DialogHelper.createDialogSingleChoice(this@MainActivity, listenerDialog).show()
    }

    private val listenerDialog = DialogInterface.OnClickListener { dialog, which ->
        run {

            when (which) {
                0 -> {
                    loadMainFragment(Const.type_pop, Const.type_pop)
                }
                1 -> {
                    loadMainFragment(Const.type_top, Const.type_top)
                }
            }
            dialog.dismiss()
        }
    }


    private fun loadMainFragment(type:String, tag:String){
        supportFragmentManager.beginTransaction().replace(R.id.main_container, MainFragment.instance(type)).commit()
    }
}
