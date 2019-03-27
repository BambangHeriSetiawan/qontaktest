package com.simxid.qontaktest.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by simx on 27,March,2019
 */
class AdapterMovie(var movies: List<String>): RecyclerView.Adapter<AdapterMovie.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movies[position])
    }
    private fun update(data: List<String>) {
        this.movies = data
        notifyDataSetChanged()
    }


    class Holder(var binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(movie: String) {

        }

    }
}