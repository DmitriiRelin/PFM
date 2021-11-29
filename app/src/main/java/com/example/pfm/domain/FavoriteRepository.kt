package com.example.pfm.domain

import com.example.pfm.domain.entites.People

interface FavoriteRepository {

    suspend fun addToFavorite(people: People)

    suspend fun deleteFavorite(people: People)

    suspend fun getFavoriteList(): List<People>
}