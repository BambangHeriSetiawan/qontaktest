package com.simxid.data.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.simxid.data.Const
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by simx on 27,March,2019
 */
interface Apis {

    @Headers("Accept: application/json", "Content-type: application/json")
    @GET(Const.url_popular_movie)
    fun popular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ):Observable<String>

    @Headers("Accept: application/json", "Content-type: application/json")
    @GET(Const.url_top_rated_movie)
    fun topRate(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ):Observable<String>


    /*#########################*/
    object Factory {
        /**
         * Config GSON
         * @return
         */
        private val gson: Gson
            get() {
                val gsonBuilder = GsonBuilder()
                gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                return gsonBuilder.create()
            }

        fun create(base_url: String): Apis {
            return getRetrofitConfig(base_url).create(Apis::class.java)
        }

        /**
         * Config retrofilt
         * @return
         */
        private fun getRetrofitConfig(base_url: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client())
                .build()
        }

        /**
         * Config OkhttpClient and interceptions
         * @return
         */
        private fun client(): OkHttpClient {
            return OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(
                    HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                )

                .build()
        }


    }

}