package com.example.pfm.ui.home.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.pfm.R
import com.example.pfm.domain.entites.People

class HomeAdapter(
    private val onItemClickListener: (People) -> Unit,
) : ListAdapter<People, HomeHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeHolder {
        return HomeHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.people_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: HomeHolder, position: Int) {
        viewHolder.itemView.setOnClickListener {
            onItemClickListener.invoke(getItem(position))
        }
        viewHolder.onBind(getItem(position))
    }
}