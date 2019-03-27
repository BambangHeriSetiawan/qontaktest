package com.simxid.qontaktest.ui.fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simxid.data.local.FavoriteItem
import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.ItemFavMovieBinding

/**
 * Created by simx on 27,March,2019
 */
class AdapterFavMovie(var movies: List<FavoriteItem>, var listener: AdapterFavMovie.OnAdapterMovieListener): RecyclerView.Adapter<AdapterFavMovie.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemFavMovieBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_fav_movie,parent,false)))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener { listener.onAdapterMovieClicked(movies[position]) }
    }
    fun update(data: List<FavoriteItem>) {
        this.movies = data
        notifyDataSetChanged()
    }

    interface OnAdapterMovieListener {
        fun onAdapterMovieClicked(movie: FavoriteItem)
    }

    class Holder(var binding: ItemFavMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: FavoriteItem) {
            with(binding) {
                itemFavVm = ItemFavMovieViewModel(movie)
                executePendingBindings()
            }
        }

    }
}