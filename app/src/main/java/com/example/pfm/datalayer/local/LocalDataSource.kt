package com.example.pfm.datalayer.local

import com.example.pfm.datalayer.local.dto.FavoritePeople
import com.example.pfm.domain.entites.People
import javax.inject.Inject

class LocalDataSource @Inject constructor(val favoriteDao: FavoriteDao, val cacheDao: CacheDao) {

    suspend fun getAllPeoples(): List<FavoritePeople> {
        return favoriteDao.getAllPeoples()
    }

    suspend fun insert(people: People) {
        return favoriteDao.insert(convertPeopleToFavoritePeople(people))
    }

    suspend fun delete(people: People) {
        return favoriteDao.delete(convertPeopleToFavoritePeople(people))
    }

    private fun convertPeopleToFavoritePeople(people: People): FavoritePeople {
        return FavoritePeople(
            id = people.id,
            email = people.email,
            first_name = people.firstName,
            last_name = people.lastName,
            avatar = people.avatar
        )
    }

}