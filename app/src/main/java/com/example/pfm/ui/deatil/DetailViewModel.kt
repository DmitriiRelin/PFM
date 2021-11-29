package com.example.pfm.ui.deatil

import androidx.lifecycle.*
import com.example.pfm.domain.entites.People
import com.example.pfm.domain.usecases.DeleteFavoritePeopleUseCase
import com.example.pfm.domain.usecases.InsertFavoritePeopleUseCase
import com.example.pfm.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailVieModel @Inject constructor(
    private val insertFavoritePeopleUseCase: InsertFavoritePeopleUseCase,
    private val deleteFavoritePeopleUseCase: DeleteFavoritePeopleUseCase,
) : ViewModel() {
    val firstNameLiveData = MutableLiveData<String>()
    val lastNameLiveData = MutableLiveData<String>()
    val emailLiveData = MutableLiveData<String>()

    private val _peopleLiveData = MutableLiveData<People>()
    val peopleLiveData: LiveData<People> = _peopleLiveData

    val editFailureEvent = MutableLiveData<Event<String>>()

    fun savePeopleChanges() {
        viewModelScope.launch {
            _peopleLiveData.value?.let {
                val firstNameValue = firstNameLiveData.value
                val lastNameValue = lastNameLiveData.value
                val emailValue = emailLiveData.value
                if (firstNameValue == null || firstNameValue.isEmpty()) {
                    editFailureEvent.value = Event("first name")
                    return@let
                }
                if (lastNameValue == null || lastNameValue.isEmpty()) {
                    editFailureEvent.value = Event("last name")
                    return@let
                }
                if (emailValue == null || emailValue.isEmpty()) {
                    editFailureEvent.value = Event("email")
                    return@let
                }

                insertFavoritePeopleUseCase.insertPeople(
                    it.copy(
                        firstName = firstNameValue,
                        lastName = lastNameValue,
                        email = emailValue
                    )
                )
            }

        }
    }



    fun start(people: People) {
        _peopleLiveData.value = people
    }


    fun changeFavoriteStatus() {
        _peopleLiveData.value?.let {
            viewModelScope.launch {
                if (it.isInFavorite == false) {
                    insertFavoritePeopleUseCase.insertPeople(it)
                } else {
                    deleteFavoritePeopleUseCase.delete(it)
                }
                _peopleLiveData.value = it.copy(isInFavorite = !it.isInFavorite)
            }
        }
    }
}


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory @Inject constructor(
    private val insertFavoritePeopleUseCase: InsertFavoritePeopleUseCase,
    private val deleteFavoritePeopleUseCase: DeleteFavoritePeopleUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            InsertFavoritePeopleUseCase::class.java,
            DeleteFavoritePeopleUseCase::class.java,
        )
            .newInstance(
                insertFavoritePeopleUseCase,
                deleteFavoritePeopleUseCase,
            )
    }
}