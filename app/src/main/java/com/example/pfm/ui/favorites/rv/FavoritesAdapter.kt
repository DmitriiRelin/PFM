package com.example.pfm.ui.favorites.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.pfm.R
import com.example.pfm.domain.entites.People
import com.example.pfm.ui.home.rv.DiffUtilCallBack
import com.example.pfm.ui.home.rv.HomeHolder

class FavoritesAdapter(
    private val onItemClickListenerFavorites: (People) -> Unit,
    private val onItemClickDelete: (People) -> Unit,
) : ListAdapter<People, FavoritesHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        return FavoritesHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_item, parent, false),
            onItemClickListenerFavorites,
            onItemClickDelete)
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        holder.bindData(getItem(position))
    }


}