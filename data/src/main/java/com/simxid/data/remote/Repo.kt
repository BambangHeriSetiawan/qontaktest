package com.simxid.data.remote

import com.simxid.data.BuildConfig
import com.simxid.data.Const
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by simx on 27,March,2019
 */
object Repo {
    fun popular(page: Int): Observable<String> {
        return Apis.Factory.create(BuildConfig.BASE_URL).popular(BuildConfig.MOVIE_DB_KEY,page,Const.language)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    fun topRate(page: Int): Observable<String> {
        return Apis.Factory.create(BuildConfig.BASE_URL).topRate(BuildConfig.MOVIE_DB_KEY,page,Const.language)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}