package com.simxid.data.local

import com.simxid.data.remote.models.ResultsItem
import com.simxid.data.remote.models.ResultsItem_
import io.objectbox.Box
import io.objectbox.Property
import io.objectbox.android.AndroidScheduler
import io.objectbox.kotlin.query
import io.objectbox.query.QueryBuilder
import io.objectbox.reactive.SubscriptionBuilder
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by simx on 27,March,2019
 */
object LocalRepo {
    /**
     * Create instance Favorite Box
     */
    private fun favMovieBox(): Box<FavoriteItem>? {
        return ObjectBox.box.boxFor(FavoriteItem::class.java)
    }

    /**
     * Create instance Popular Box
     */
    private fun popMovieBox(): Box<ResultsItem>? {
        return ObjectBox.box.boxFor(ResultsItem::class.java)
    }

    /**
     * Create instance Top Rating Box
     */
    private fun topMovieBox(): Box<ResultsItem>? {
        return ObjectBox.box.boxFor(ResultsItem::class.java)
    }


    /**
     * Create instance Query Popular Movie
     */
    fun movieQueryPopByTitle(name:String): SubscriptionBuilder<MutableList<ResultsItem>>? {
        return popMovieBox()!!.query().equal(ResultsItem_.title, name).build().subscribe().on(AndroidScheduler.mainThread())
    }


    /**
     * Create instance Query Top Rating Movie
     */
    fun movieQueryTopByTitle(name:String): SubscriptionBuilder<MutableList<ResultsItem>>? {
        return popMovieBox()!!.query().equal(ResultsItem_.title, name).build().subscribe().on(AndroidScheduler.mainThread())
    }

    fun getTopMovies(): SubscriptionBuilder<MutableList<ResultsItem>>? {
        return topMovieBox()?.query { order(ResultsItem_.title) }!!.subscribe().on(AndroidScheduler.mainThread())
    }
    fun getPopMovies(): SubscriptionBuilder<MutableList<ResultsItem>>? {
        return popMovieBox()?.query { order(ResultsItem_.title) }!!.subscribe().on(AndroidScheduler.mainThread())
    }
    fun storeLocalPop(resultsItem: ResultsItem): Completable? {
        return Completable.fromAction { popMovieBox()!!.put(resultsItem) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    fun storeLocalPop(resultsItem: List<ResultsItem>): Completable? {
        return Completable.fromAction { popMovieBox()!!.put(resultsItem) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    fun storeLocalTop(resultsItem: ResultsItem): Completable? {
        return Completable.fromAction { popMovieBox()!!.put(resultsItem) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    fun storeLocalTop(resultsItem: List<ResultsItem>): Completable? {
        return Completable.fromAction { topMovieBox()!!.put(resultsItem) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }


    fun addFav(resultsItem: ResultsItem): Completable? {
        return Completable.fromAction { popMovieBox()!!.put(resultsItem) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    fun removeFav(resultsItem: ResultsItem): Completable? {
        return Completable.fromAction { popMovieBox()!!.remove(resultsItem) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Add to Favorite Movie
     */
    fun addFav(favoriteItem: FavoriteItem): Completable? {
        return Completable.fromAction { favMovieBox()!!.put(favoriteItem) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    /**
     * Delete from Favorite Movie
     */
    fun devFav(favoriteItem: FavoriteItem): Completable? {
        return Completable.fromAction { favMovieBox()!!.remove(favoriteItem) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    /**
     * GET ALL  Favorite Movie
     */

    fun getFavMovies(): SubscriptionBuilder<MutableList<FavoriteItem>>? {
        return favMovieBox()?.query { order(FavoriteItem_.title) }!!.subscribe().on(AndroidScheduler.mainThread())
    }

    /**
     * Search Movie By Title
     */

    fun searchFavMovies(title:String): SubscriptionBuilder<MutableList<FavoriteItem>>? {
        return favMovieBox()?.query()!!.contains(FavoriteItem_.title,title).build()!!.subscribe().on(AndroidScheduler.mainThread())
    }
}

