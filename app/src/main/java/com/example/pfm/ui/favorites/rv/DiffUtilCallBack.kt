package com.example.pfm.ui.favorites.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.pfm.domain.entites.People

class DiffUtilCallBack : DiffUtil.ItemCallback<People>() {

    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem == newItem
    }

}