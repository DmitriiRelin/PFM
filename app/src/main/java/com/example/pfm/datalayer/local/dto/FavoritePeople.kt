package com.example.pfm.datalayer.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoritePeoples")
data class FavoritePeople(
    @PrimaryKey
    val id: Int,

    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String,

)