package com.example.pfm.domain

import com.example.pfm.domain.entites.People
import kotlinx.coroutines.flow.Flow

interface PeoplesRepository {

    fun getListPeoples(needRefresh: Boolean): Flow<List<People>>

}