package com.simxid.qontaktest.ui.main.fragment

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.simxid.data.Const
import com.simxid.data.local.LocalRepo
import com.simxid.data.remote.Repo
import com.simxid.data.remote.models.ResultsItem
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by simx on 27,March,2019
 */
class MainFragmentViewModel(var disposable: CompositeDisposable): BaseObservable() {

    @Bindable var errorMsg = MutableLiveData<String>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.errorMsg)
        }

    @Bindable var movies = MutableLiveData<List<ResultsItem>>()
    set(value) {
        field = value
        notifyPropertyChanged(BR.movies)
    }

    @Bindable var isLoading = ObservableField<Boolean>()
    set(value) {
        field = value
        notifyPropertyChanged(BR.isLoading)
    }

    fun getRemoteData(type: String, page: Int) {
        when (type) {
            Const.type_top -> {
                getTopMovie(page)
            }
            Const.type_pop -> {
                getPopMoview(page)
            }
        }
    }



    @SuppressLint("CheckResult")
    private fun getTopMovie(page: Int) {
        isLoading.set(true)
        Repo.topRate(page)?.subscribe({
            isLoading.set(false)
            if (it.results != null) {
                movies.postValue(it.results)
                LocalRepo.storeLocalTop(it.results!!)
            } else errorMsg.postValue(it.statusMessage)
        },{
            isLoading.set(false)
            errorMsg.postValue(it.message)
        })
    }

    @SuppressLint("CheckResult")
    private fun getPopMoview(page: Int) {
        isLoading.set(true)
        Repo.popular(page)?.subscribe({
            isLoading.set(false)
            if (it.results != null) {
                movies.postValue(it.results)
                LocalRepo.storeLocalPop(it.results!!)?.subscribe({

                },{
                 Log.e("MainFragmentViewModel","getPopMoview -> $it")
                })
            } else errorMsg.postValue(it.statusMessage)
        },{
            isLoading.set(false)
            errorMsg.postValue(it.message)
        })
    }

    fun getLocalData(type: String) {
        when (type) {
            Const.type_top -> {
                getTopLocalMovie()
            }
            Const.type_pop -> {
                getPopLocalMovie()
            }
        }
    }

    private fun getPopLocalMovie(){
        LocalRepo.getPopMovies()?.onError {
            errorMsg.postValue(it.message)
        }?.observer {
            movies.postValue(it)
        }
    }
    private fun getTopLocalMovie(){
        LocalRepo.getTopMovies()?.onError {
            errorMsg.postValue(it.message)
        }?.observer {
          movies.postValue(it)
        }
    }




    fun clear() {
        if (!disposable.isDisposed)disposable.dispose()
    }

}