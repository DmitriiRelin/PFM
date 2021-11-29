package com.example.pfm.datalayer.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pfm.datalayer.local.dto.CachePeople
import com.example.pfm.datalayer.local.dto.FavoritePeople

@Database(entities = [FavoritePeople::class, CachePeople::class], version = 1, exportSchema = false)
abstract class PeoplesDB : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun cacheDao(): CacheDao
}