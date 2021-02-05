package com.kalu.disaster_alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kalu.disaster_alarm.databinding.DisasterBinding
import com.kalu.models.Disaster

class DisasterFeedAdapter :
    ListAdapter<Disaster, DisasterFeedAdapter.DisasterViewHolder>(DisasterCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterViewHolder {
        return DisasterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DisasterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class DisasterViewHolder(val binding: DisasterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Disaster, position: Int) {
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
}

class DisasterCallBack : DiffUtil.ItemCallback<Disaster>() {
    override fun areItemsTheSame(oldItem: Disaster, newItem: Disaster): Boolean {
        return oldItem.disaster_id == newItem.disaster_id
    }

    override fun areContentsTheSame(oldItem: Disaster, newItem: Disaster): Boolean {
        return oldItem == newItem
    }
}
