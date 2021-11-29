package com.example.pfm.datalayer.remote

import com.example.pfm.datalayer.remote.dto.ApiResponse
import retrofit2.http.GET

interface PeopleApi {
    @GET("users?page=2")
    suspend fun getPeoplesList(): ApiResponse
}