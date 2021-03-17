package com.kalu.extensions

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kalu.models.Disaster
import com.kalu.ui.R


@BindingAdapter("setVillage")
fun TextView.setDisasterDescription(item: Disaster){
    item.let {
        text = it.village
    }
}

@BindingAdapter("setDisasterImage")
fun setDisasterImage(disasterImg: ImageView, item: Disaster){

    item.disaster_img_url_one.let {
        val uri = it.toUri().buildUpon().scheme("https").build()
        Log.d("cheki image", uri.toString())
        Glide.with(disasterImg.context)
            .load(uri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(disasterImg)
    }
}


