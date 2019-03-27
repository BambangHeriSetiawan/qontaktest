package com.simxid.qontaktest.ui.main.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.DetailMovieActivityBinding


class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding:DetailMovieActivityBinding
    private lateinit var vm:DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.detail_movie_activity)
        vm = DetailMovieViewModel()
        binding.lifecycleOwner = this
        binding.detailMovieVm = vm
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menu?.findItem(R.id.nav_un_fav)!!.isVisible = true
        return super.onCreateOptionsMenu(menu)
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
       private const val KEY_MOVIE = "movie"
        fun start(context: Context, movie:String){
            var intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(KEY_MOVIE,movie)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }
    }
}
