package com.example.pfm.ui.home

import androidx.lifecycle.*
import com.example.pfm.domain.entites.People
import com.example.pfm.domain.usecases.GetListPeopleUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getListPeopleUseCase: GetListPeopleUseCase) :
    ViewModel() {

    private val _listLiveData = MutableLiveData<List<People>>()
    val listLiveData: LiveData<List<People>> = _listLiveData

    fun getList() {
        viewModelScope.launch {
            getListPeopleUseCase.getListPeople().collect {
                _listLiveData.value = it
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(private val getListPeopleUseCase: GetListPeopleUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetListPeopleUseCase::class.java)
            .newInstance(getListPeopleUseCase)
    }
}