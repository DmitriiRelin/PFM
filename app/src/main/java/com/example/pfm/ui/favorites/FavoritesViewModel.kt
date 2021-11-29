package com.example.pfm.ui.favorites

import androidx.lifecycle.*
import com.example.pfm.domain.entites.People
import com.example.pfm.domain.usecases.DeleteFavoritePeopleUseCase
import com.example.pfm.domain.usecases.GetAllFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val deleteFavoritePeopleUseCase: DeleteFavoritePeopleUseCase,
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase,
) : ViewModel() {

    private val _listPeopleLiveData = MutableLiveData<List<People>>()
    val listPeopleLiveData: LiveData<List<People>> = _listPeopleLiveData


     fun getList() {
        viewModelScope.launch {
            _listPeopleLiveData.value = getAllFavoriteUseCase.getAllList()
        }
    }

    fun delete(people: People) {
        viewModelScope.launch {
           deleteFavoritePeopleUseCase.delete(people)
            getList()
        }
    }

}

@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory @Inject constructor(
    private val deleteFavoritePeopleUseCase: DeleteFavoritePeopleUseCase,
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            DeleteFavoritePeopleUseCase::class.java,
            GetAllFavoriteUseCase::class.java
        )
            .newInstance(
                deleteFavoritePeopleUseCase,
                getAllFavoriteUseCase
            )
    }
}