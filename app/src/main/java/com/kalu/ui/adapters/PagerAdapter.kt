package com.kalu.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kalu.models.Disaster
import com.kalu.models.ImageItem
import com.kalu.ui.R
import com.makeramen.roundedimageview.RoundedImageView

class PagerAdapter(private val disaster: Disaster) :
    RecyclerView.Adapter<PagerAdapter.SliderViewHolder>() {

    private val sliderImageItems = listOf<String>(
        disaster.disaster_img_url_two,
        disaster.disaster_img_url_three,
        disaster.disaster_img_url_four,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.slide_image_item_container, parent, false)
        )

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val itemUrl = sliderImageItems[position]
        bindImage(holder, itemUrl)
        }

    private fun bindImage(holder: SliderViewHolder, itemUrl: String) {
        val roundImage = holder.itemView.findViewById<RoundedImageView>(R.id.imageSlide)
        val uri = itemUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(roundImage.context)
            .load(uri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(roundImage)
    }

    override fun getItemCount() = sliderImageItems.size



    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private class DiffCallback : DiffUtil.ItemCallback<ImageItem>() {
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem == newItem
        }
    }

}
