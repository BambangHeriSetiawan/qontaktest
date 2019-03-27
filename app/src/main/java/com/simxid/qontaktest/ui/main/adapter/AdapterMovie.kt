package com.simxid.qontaktest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.ItemMovieBinding

/**
 * Created by simx on 27,March,2019
 */
class AdapterMovie(var movies: List<String>, var listener: AdapterMovie.OnAdapterMovieListener): RecyclerView.Adapter<AdapterMovie.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMovieBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener { listener }
        holder.bind(movies[position])
    }
    private fun update(data: List<String>) {
        this.movies = data
        notifyDataSetChanged()
    }

    interface OnAdapterMovieListener {
        fun onAdapterMovieClicked(movie: String)
    }

    class Holder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: String) {
            with(binding) {
                itemMovieVm = ItemMovieViewModel(movie)
                executePendingBindings()
            }
        }

    }
}