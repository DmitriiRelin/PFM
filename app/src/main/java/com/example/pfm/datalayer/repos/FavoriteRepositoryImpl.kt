package com.example.pfm.datalayer.repos

import com.example.pfm.datalayer.local.LocalDataSource
import com.example.pfm.domain.FavoriteRepository
import com.example.pfm.domain.entites.People
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    FavoriteRepository {

    override suspend fun addToFavorite(people: People) {
        localDataSource.insert(people)
    }

    override suspend fun deleteFavorite(people: People) {
        localDataSource.delete(people)
    }

    override suspend fun getFavoriteList(): List<People> {
        return localDataSource.getAllPeoples().map { favoritePeople ->
            People(
                id = favoritePeople.id,
                email = favoritePeople.email,
                firstName = favoritePeople.first_name,
                lastName = favoritePeople.last_name,
                avatar = favoritePeople.avatar,
                isInFavorite = true)
        }
    }
}