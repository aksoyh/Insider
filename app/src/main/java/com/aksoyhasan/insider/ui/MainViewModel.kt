package com.aksoyhasan.insider.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aksoyhasan.insider.base.BaseViewModel
import com.aksoyhasan.insider.data.Resource
import com.aksoyhasan.insider.data.dto.StarDTO
import com.aksoyhasan.insider.data.repositorySource.InsiderRepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insiderRepositorySource: InsiderRepositorySource
) : BaseViewModel() {

    private val starDataPrivate = MutableLiveData<Resource<StarDTO>>()
    val starLiveData: LiveData<Resource<StarDTO>> get() = starDataPrivate

    var dataLoaded: Boolean = false

    fun mockDataLoading(): Boolean {
        viewModelScope.launch {
            delay(5000)
            dataLoaded = true
        }
        return dataLoaded
    }

    fun getStarObj(isSmall: Boolean) {
        viewModelScope.launch {
            starDataPrivate.value = Resource.Loading()
            insiderRepositorySource.getStarObj(isSmall).collect {
                starDataPrivate.value = it
            }
        }
    }

}
