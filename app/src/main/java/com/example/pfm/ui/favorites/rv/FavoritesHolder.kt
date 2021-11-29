package com.example.pfm.ui.favorites.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pfm.utils.loadImage
import com.example.pfm.databinding.FavoriteItemBinding
import com.example.pfm.domain.entites.People
import com.zerobranch.layout.SwipeLayout


class FavoritesHolder(
    itemView: View,
    private val onItemClickListenerFavorites: (People) -> Unit,
    private val onItemClickDelete: (People) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    private val binding: FavoriteItemBinding = FavoriteItemBinding.bind(itemView)

    fun bindData(people: People) {
        binding.firstName.text = people.firstName
        binding.lastName.text = people.lastName
        loadImage(itemView.context, people.avatar, binding.imageView)

        if (binding.rightView != null) {
            binding.rightView.setOnClickListener(View.OnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickDelete(people)
                }
            })
        }

        binding.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener {
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if (direction == SwipeLayout.LEFT && isContinuous) {
                    if (adapterPosition != RecyclerView.NO_POSITION) {

                    }
                }
            }

            override fun onClose() {

            }

        })

        binding.dragItem.setOnClickListener {
            onItemClickListenerFavorites(people)
        }
    }

}