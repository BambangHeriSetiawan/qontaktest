package com.simxid.qontaktest.helper

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.simxid.qontaktest.R
import com.squareup.picasso.Picasso

/**
 * Created by simx on 27,March,2019
 */
class BindAdapter {
    companion object {
        @BindingAdapter("bind:image_url") @JvmStatic fun loadImage(imageView: ImageView, url:String?){
            Picasso.get().load(url).error(R.mipmap.ic_launcher_round).into(imageView)
        }
        @BindingAdapter("bind:visibility")
        @JvmStatic fun setVisibility(view: View, value: Boolean?) {
            view.visibility = if (value!!) View.VISIBLE else View.GONE
        }

    }
}