package com.simxid.qontaktest.ui.fav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.rcvFav.let {
            it.hasFixedSize()
            it.itemAnimator = DefaultItemAnimator()
            it.layoutManager = GridLayoutManager(this@FavoriteActivity,2)
        }
    }
}
