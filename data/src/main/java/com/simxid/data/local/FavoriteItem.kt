package com.simxid.data.local


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.simxid.data.BuildConfig
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class FavoriteItem(

	@field:SerializedName("idL")
    @Id(assignable = true) var idL: Long? = null,

    @field:SerializedName("overview")
	val overview: String? = null,

    @field:SerializedName("original_language")
	val originalLanguage: String? = null,

    @field:SerializedName("original_title")
	val originalTitle: String? = null,

    @field:SerializedName("video")
	val video: Boolean? = null,

    @field:SerializedName("title")
	val title: String? = null,

/*
    @field:SerializedName("genre_ids")
	val genreIds: List<Int?>? = null,
*/

    @field:SerializedName("poster_path")
	val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

    @field:SerializedName("release_date")
	val releaseDate: String? = null,

    @field:SerializedName("popularity")
	val popularity: Double? = null,

    @field:SerializedName("vote_average")
	val voteAverage: Double? = null,

    @field:SerializedName("id")
    val idMovie: Int? = null,


    @field:SerializedName("adult")
	val adult: Boolean? = null,

    @field:SerializedName("vote_count")
	val voteCount: Int? = null
) : Parcelable {
	fun getImagePoster():String {
		return BuildConfig.PATH_IMAGE + posterPath
	}
	fun getImageBackdrop():String {
		return BuildConfig.PATH_IMAGE + backdropPath
	}
}