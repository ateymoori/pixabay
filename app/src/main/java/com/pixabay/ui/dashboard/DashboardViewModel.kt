package com.pixabay.ui.dashboard

import androidx.lifecycle.MutableLiveData
import com.pixabay.repo.repo.MainRepo
import com.pixabay.utils.Cons.Companion.DEFAULT_SEARCH_WORD
import com.pixabay.utils.base.BaseViewModel
import com.pixabay.utils.entities.ImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo
) :
    BaseViewModel() {

    // dispatches execution into Android main thread
    //val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
//
//    // represent a pool of shared threads as coroutine dispatcher
//    val bgDispatcher: CoroutineDispatcher = Dispatchers.I0


    val results = MutableLiveData<List<ImageModel>>()

    init {
        // search(DEFAULT_SEARCH_WORD)
        searchNew(DEFAULT_SEARCH_WORD)
    }

    fun searchNew(word: String) = runBlocking {
        withContext(Dispatchers.IO) {
            val list = mainRepo.newSearch(word)
            results.postValue(list)
        }
    }

    fun search(word: String) {
//         uiScope.launch {
//            view.showLoading() // ui thread
//
//
//        val result = task.await()
//
//             view.showData(result) // ui thread
//    }
//

        mainRepo.search(word)
    }
}