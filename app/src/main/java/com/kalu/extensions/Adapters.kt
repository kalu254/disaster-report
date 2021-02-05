package com.kalu.extensions

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kalu.models.Disaster

@BindingAdapter("setVillage")
fun TextView.setDisasterDescription(item: Disaster){
    item.let {
        text = it.village
    }
}

@BindingAdapter("setDisasterImage")
fun setDisasterImage(disasterImg: ImageView, item: Disaster){

    item.disaster_img_url_one.let {
        val uri = it.toUri().buildUpon().scheme("http").build()
        Log.d("cheki image",uri.toString())
        Glide.with(disasterImg.context)
            .load(uri)
            .into(disasterImg)
    }
}
