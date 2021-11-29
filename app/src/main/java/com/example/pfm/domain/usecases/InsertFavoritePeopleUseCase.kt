package com.example.pfm.domain.usecases

import com.example.pfm.domain.FavoriteRepository
import com.example.pfm.domain.entites.People
import javax.inject.Inject

class InsertFavoritePeopleUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    suspend fun insertPeople(people: People) {
        favoriteRepository.addToFavorite(people)
    }

}