package com.simxid.qontaktest.ui.main.fragment

import android.annotation.SuppressLint
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.simxid.data.Const
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


    fun getMovie(type:String, page: Int) {
        when (type) {
            Const.type_pop -> {
                getPopMoview(page)
            }
            Const.type_top -> {
                getTopMovie(page)
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun getTopMovie(page: Int) {
        Repo.topRate(page)?.subscribe({
            if (it.results != null) movies.postValue(it.results)
                else errorMsg.postValue(it.statusMessage)
        },{ errorMsg.postValue(it.message) })
    }

    @SuppressLint("CheckResult")
    private fun getPopMoview(page: Int) {
        Repo.popular(page)?.subscribe({
            if (it.results != null) movies.postValue(it.results)
                else errorMsg.postValue(it.statusMessage)
        },{ errorMsg.postValue(it.message) })
    }
    fun clear() {
        if (!disposable.isDisposed)disposable.dispose()
    }
}