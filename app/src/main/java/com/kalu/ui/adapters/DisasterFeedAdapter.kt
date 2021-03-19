package com.kalu.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kalu.ui.databinding.DisasterBinding
import com.kalu.models.Disaster

class DisasterFeedAdapter ( val onClickListener: OnClickListener):
    ListAdapter<Disaster, DisasterFeedAdapter.DisasterViewHolder>(DisasterCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterViewHolder {
        return DisasterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DisasterViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class DisasterViewHolder(val binding: DisasterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Disaster) {
            binding.disaster = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DisasterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DisasterBinding.inflate(layoutInflater, parent, false)
                return DisasterViewHolder(binding)
            }
        }

    }


    class OnClickListener(val clickListener: (disaster:Disaster) -> Unit) {
        fun onClick(disaster: Disaster) = clickListener(disaster)
    }
}

class DisasterCallBack : DiffUtil.ItemCallback<Disaster>() {
    override fun areItemsTheSame(oldItem: Disaster, newItem: Disaster): Boolean {
        return oldItem.disaster_id == newItem.disaster_id
    }

    override fun areContentsTheSame(oldItem: Disaster, newItem: Disaster): Boolean {
        return oldItem == newItem
    }


}
