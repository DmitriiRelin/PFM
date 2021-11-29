package com.example.pfm.datalayer.local

import androidx.room.*
import com.example.pfm.datalayer.local.dto.CachePeople

@Dao
interface CacheDao {

    @Query("SELECT * FROM CachePeoples")
    suspend fun allPeoples(): List<CachePeople>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoritePeoples: List<CachePeople>)

    @Delete
    suspend fun delete(favoritePeople: CachePeople)

}