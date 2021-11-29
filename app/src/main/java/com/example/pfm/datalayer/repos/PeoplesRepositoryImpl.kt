package com.example.pfm.datalayer.repos

import com.example.pfm.datalayer.local.LocalDataSource
import com.example.pfm.datalayer.local.dto.CachePeople
import com.example.pfm.datalayer.remote.RemoteDataSource
import com.example.pfm.domain.PeoplesRepository
import com.example.pfm.domain.entites.People
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeoplesRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : PeoplesRepository {

    override fun getListPeoples(needRefresh: Boolean): Flow<List<People>> = flow {
        val favoriesPeoples = localDataSource.favoriteDao.getAllPeoples()
        val listFromDb = localDataSource.cacheDao.allPeoples().map { people ->
            People(people.id, people.email, people.first_name, people.last_name, people.avatar,
                favoriesPeoples.find { people.id == it.id } != null)
        }
        if (needRefresh) {
            if (listFromDb.isNotEmpty())
                emit(listFromDb)
            val listFromApi = dataSource.getListPeoples().data
            localDataSource.cacheDao.insert(listFromApi.map {
                CachePeople(it.id, it.email, it.first_name, it.last_name, it.avatar)
            })
            emit(listFromApi.map { people ->
                People(people.id, people.email, people.first_name, people.last_name, people.avatar,
                    favoriesPeoples.find { people.id == it.id } != null)
            })
        } else {
            emit(listFromDb)
        }

    }

}