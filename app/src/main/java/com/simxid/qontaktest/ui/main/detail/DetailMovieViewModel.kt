package com.simxid.qontaktest.ui.main.detail

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.simxid.data.remote.models.ResultsItem

/**
 * Created by simx on 27,March,2019
 */
class DetailMovieViewModel(): BaseObservable() {
    @Bindable
    var movie = MutableLiveData<ResultsItem>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.movie)
        }

    @Bindable var image = ObservableField<String>()
    @Bindable var title = ObservableField<String>()
    @Bindable var desc = ObservableField<String>()
    @Bindable var releaseDate = ObservableField<String>()
    @Bindable var rate = ObservableField<String>()
}