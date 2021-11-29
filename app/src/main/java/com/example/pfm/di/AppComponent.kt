package com.example.pfm.di

import android.content.Context
import com.example.pfm.ui.deatil.DetailFragment
import com.example.pfm.ui.favorites.FavoritesFragment
import com.example.pfm.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [PeopleModule::class, FavoriteModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

    fun inject(homeFragment: HomeFragment)
    fun inject(detailFragment: DetailFragment)
    fun inject(favoritesFragment: FavoritesFragment)
}