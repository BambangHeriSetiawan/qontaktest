package com.simxid.qontaktest.ui.main.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.simxid.data.remote.models.ResultsItem

/**
 * Created by simx on 27,March,2019
 */
class ItemMovieViewModel(movie: ResultsItem): BaseObservable() {
    @Bindable var image = ObservableField<String> (movie.getImagePoster())
    @Bindable var title = ObservableField<String> (movie.title)
    @Bindable var desc = ObservableField<String> (movie.overview)
    @Bindable var rating = ObservableField<Double> (movie.voteAverage)

}