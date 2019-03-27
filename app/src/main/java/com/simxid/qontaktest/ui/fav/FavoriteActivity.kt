package com.simxid.qontaktest.ui.fav

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.simxid.data.local.FavoriteItem
import com.simxid.data.remote.models.ResultsItem
import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.FavoriteActivityBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding:FavoriteActivityBinding
    private lateinit var vm:FavoritViewModel
    private lateinit var adapterFavMovie: AdapterFavMovie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.favorite_activity)
        vm = FavoritViewModel()
        binding.lifecycleOwner = this
        binding.favVm = vm
        vm.getFavMovies()
        adapterFavMovie = AdapterFavMovie(listOf(), listener)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        vm.movies.observe(this, Observer {
            adapterFavMovie.update(it)
        })
        binding.rcvFav.let {
            it.hasFixedSize()
            it.adapter = adapterFavMovie
            it.itemAnimator = DefaultItemAnimator()
            it.layoutManager = GridLayoutManager(this@FavoriteActivity,2)
        }
    }
    private val listener = object : AdapterFavMovie.OnAdapterMovieListener {


        override fun onAdapterMovieClicked(movie: FavoriteItem) {

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        var menuItem = menu!!.findItem(R.id.nav_search)
        var searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView: SearchView? = null
        if (menuItem != null) {
            searchView = menuItem.actionView as SearchView
            searchView.queryHint = "Title Movie"
            searchView.setOnQueryTextListener(searchListener)
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this@FavoriteActivity.componentName))
        }

        return super.onCreateOptionsMenu(menu)
    }

    private val searchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText!!.isEmpty()) vm.getFavMovies()
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            vm.searchFavMovies(query!!)
            return true
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
