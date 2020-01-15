package com.pixabay.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pixabay.repo.repo.MainRepo
import com.pixabay.utils.Cons.Companion.DEFAULT_SEARCH_WORD
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo
) :
    BaseViewModel<DashboardContract>() {

    val result = MutableLiveData<Response<Any?>>()

    override fun onViewCreated() {
        super.onViewCreated()
        setTag(DEFAULT_SEARCH_WORD)
    }

    //using Coroutine for handle threads
    fun search(word: String) {
        viewModelScope.launch {
            result.value = Loading(null)
            result.value = Success(data = mainRepo.newSearch(word))
        }

    }



    //navigator is WeakReference to ViewLayer
    //it's just a sample to showing how to connect presenter/ViewModel layer to view with WeakReference
    fun setTag(tag: String) {
        navigator?.enterSearchWord(tag)
    }
}