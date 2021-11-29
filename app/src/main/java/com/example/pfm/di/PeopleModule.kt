package com.example.pfm.di

import com.example.pfm.datalayer.local.LocalDataSource
import com.example.pfm.datalayer.remote.PeopleApi
import com.example.pfm.datalayer.remote.RemoteDataSource
import com.example.pfm.datalayer.repos.PeoplesRepositoryImpl
import com.example.pfm.domain.PeoplesRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
object PeopleModule {

    @Singleton
    @Provides
    fun providePeopleRepository(
        dataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): PeoplesRepository {
        return PeoplesRepositoryImpl(dataSource, localDataSource)
    }


    @Singleton
    @Provides
    fun providesCinemaApi(): PeopleApi {
        val apiService = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )
            .client(createOkHttpClient(PODInterceptor()))
            .build()
            .create(PeopleApi::class.java)
        return apiService
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        return httpClient.build()
    }

    class PODInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }

}