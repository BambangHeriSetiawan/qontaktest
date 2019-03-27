package com.simxid.qontaktest.ui.main.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.simxid.data.local.FavoriteItem
import com.simxid.data.local.LocalRepo
import com.simxid.data.remote.models.ResultsItem
import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.DetailMovieActivityBinding
import com.simxid.qontaktest.ui.fav.FavoriteActivity


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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.hasExtra(KEY_MOVIE)) vm.movie.postValue(intent.getParcelableExtra(KEY_MOVIE))

        vm.movie.observe(this, Observer {
            Log.v("DetailMovieActivity","onCreate -> $it")
            supportActionBar?.title = it.originalTitle
            vm.title.set(it.title)
            vm.desc.set(it.overview)
            vm.image.set(it.getImageBackdrop())
            vm.releaseDate.set("Release date ${it.releaseDate}")
            vm.rate.set(it.voteAverage.toString())
        })
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
            R.id.nav_un_fav -> {
                var item = FavoriteItem(
                    vm.movie.value?.id!!.toLong(),
                    vm.movie.value?.overview,
                    vm.movie.value?.originalLanguage,
                    vm.movie.value?.originalTitle,
                    vm.movie.value?.video,
                    vm.movie.value?.title,
                    vm.movie.value?.posterPath,
                    vm.movie.value?.backdropPath,
                    vm.movie.value?.releaseDate,
                    vm.movie.value?.popularity,
                    vm.movie.value?.voteAverage,
                    vm.movie.value?.idMovie,
                    vm.movie.value?.adult,
                    vm.movie.value?.voteCount

                )
                LocalRepo.addFav(item)?.subscribe {
                    FavoriteActivity.start(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
       private const val KEY_MOVIE = "movie"
        fun start(context: Context, movie:ResultsItem){
            var intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(KEY_MOVIE,movie)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }
    }
}
