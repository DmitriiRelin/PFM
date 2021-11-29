package com.example.pfm.domain.usecases

import com.example.pfm.domain.PeoplesRepository
import javax.inject.Inject

class GetListPeopleUseCase @Inject constructor(private val peoplesRepository: PeoplesRepository) {
    suspend fun getListPeople(needRefresh: Boolean = true) =
        peoplesRepository.getListPeoples(needRefresh)
}