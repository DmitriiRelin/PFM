package com.example.pfm.datalayer.remote

import com.example.pfm.datalayer.remote.dto.ApiResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val peopleApi: PeopleApi) {

    suspend fun getListPeoples(): ApiResponse {
        return peopleApi.getPeoplesList()
    }
}