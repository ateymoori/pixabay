package com.pixabay.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pixabay.repo.repo.MainRepo
import com.pixabay.utils.Cons.Companion.DEFAULT_SEARCH_WORD
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.entities.ImageModel
import com.pixabay.utils.entities.ResponseModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo
) :
    BaseViewModel() {

    val result = MutableLiveData<Response>()

    init {
        search(DEFAULT_SEARCH_WORD)
    }

    fun search(word: String) {
        viewModelScope.launch {
            result.value = Loading(null)
            result.value = Success(data = mainRepo.newSearch(word))
        }
    }

}