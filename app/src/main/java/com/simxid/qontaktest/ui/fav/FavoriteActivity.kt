package com.simxid.qontaktest.ui.fav

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.FavoriteActivityBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding:FavoriteActivityBinding
    private lateinit var vm:FavoritViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.favorite_activity)
        vm = FavoritViewModel()
        binding.lifecycleOwner = this
        binding.favVm = vm
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rcvFav.let {
            it.hasFixedSize()
            it.itemAnimator = DefaultItemAnimator()
            it.layoutManager = GridLayoutManager(this@FavoriteActivity,2)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        fun start(context: Context){
            var intent = Intent(context, FavoriteActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }
    }
}
