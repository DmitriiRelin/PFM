package com.example.pfm.di

import android.content.Context
import androidx.room.Room
import com.example.pfm.datalayer.local.CacheDao
import com.example.pfm.datalayer.local.FavoriteDao
import com.example.pfm.datalayer.local.LocalDataSource
import com.example.pfm.datalayer.local.PeoplesDB
import com.example.pfm.datalayer.repos.FavoriteRepositoryImpl
import com.example.pfm.domain.FavoriteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FavoriteModule {

    @Singleton
    @Provides
    fun provideFavoriteRepository(
        localDataSource: LocalDataSource,
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(localDataSource)
    }

    private const val DB_NAME = "Favorite.db"

    @Singleton
    @Provides
    fun provideFavoriteDao(context: Context): FavoriteDao {
        val db = Room.databaseBuilder(
            context,
            PeoplesDB::class.java,
            DB_NAME
        )
            .build()
        return db.favoriteDao()
    }


    @Singleton
    @Provides
    fun provideCacheDao(context: Context): CacheDao {
        val db = Room.databaseBuilder(
            context,
            PeoplesDB::class.java,
            DB_NAME
        )
            .build()
        return db.cacheDao()
    }

}