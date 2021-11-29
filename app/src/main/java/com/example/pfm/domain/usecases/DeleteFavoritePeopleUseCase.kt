package com.example.pfm.domain.usecases

import com.example.pfm.domain.FavoriteRepository
import com.example.pfm.domain.entites.People
import javax.inject.Inject

class DeleteFavoritePeopleUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    suspend fun delete(people: People) {
        favoriteRepository.deleteFavorite(people)
    }

}