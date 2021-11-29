package com.example.pfm.datalayer.local

import androidx.room.*
import com.example.pfm.datalayer.local.dto.FavoritePeople

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM FavoritePeoples")
    suspend fun getAllPeoples(): List<FavoritePeople>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoritePeoples: FavoritePeople)

    @Delete
    suspend fun delete(favoritePeople: FavoritePeople)

}