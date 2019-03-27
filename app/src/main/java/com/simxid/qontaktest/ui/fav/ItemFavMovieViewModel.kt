package com.simxid.qontaktest.ui.fav

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.simxid.data.local.FavoriteItem
import com.simxid.data.remote.models.ResultsItem

/**
 * Created by simx on 27,March,2019
 */
class ItemFavMovieViewModel(movie: FavoriteItem): BaseObservable() {
    @Bindable var image = ObservableField<String> (movie.getImagePoster())
    @Bindable var title = ObservableField<String> (movie.title)
    @Bindable var desc = ObservableField<String> (movie.overview)
    @Bindable var rating = ObservableField<Double> (movie.voteAverage)

}