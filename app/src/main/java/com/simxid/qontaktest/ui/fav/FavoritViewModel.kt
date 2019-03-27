package com.simxid.qontaktest.ui.fav

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.simxid.data.local.FavoriteItem
import com.simxid.data.local.LocalRepo

/**
 * Created by simx on 27,March,2019
 */
class FavoritViewModel: BaseObservable() {

    @Bindable var movies = MutableLiveData<List<FavoriteItem>> ()
    set(value) {
        field = value
        notifyPropertyChanged(BR.movies)
    }
    @Bindable var errorMsg = MutableLiveData<String>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.errorMsg)
        }

    fun getFavMovies(){
        LocalRepo.getFavMovies()?.onError {
            errorMsg.postValue(it.message)
        }?.observer {
            movies.postValue(it)
        }
    }
    fun searchFavMovies(title: String){
        Log.v("FavoritViewModel","searchFavMovies -> $title")
        LocalRepo.searchFavMovies(title)?.onError {
            errorMsg.postValue(it.message)
        }?.observer {
            movies.postValue(it)
        }
    }
}