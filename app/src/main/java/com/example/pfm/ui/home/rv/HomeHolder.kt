package com.example.pfm.ui.home.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pfm.databinding.PeopleItemBinding
import com.example.pfm.domain.entites.People

class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val binding: PeopleItemBinding = PeopleItemBinding.bind(itemView)

    fun onBind(people: People) {
        binding.peopleFirstName.text = people.firstName
        binding.peopleLastName.text = people.lastName
//            Glide.with(itemView.context)
//                .load(picture.url)
//                .placeholder(R.color.primaryColor)
//                .fitCenter()
//                .centerCrop()
//                .into(binding.seeAlsoImage)
    }
}
