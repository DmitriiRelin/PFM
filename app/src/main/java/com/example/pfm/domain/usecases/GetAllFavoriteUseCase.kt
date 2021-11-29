package com.example.pfm.domain.usecases

import com.example.pfm.domain.FavoriteRepository
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    suspend fun getAllList() = favoriteRepository.getFavoriteList()


}