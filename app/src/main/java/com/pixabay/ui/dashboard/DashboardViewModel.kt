package com.pixabay.ui.dashboard

import com.pixabay.repo.repo.MainRepo
import com.pixabay.utils.Cons.Companion.DEFAULT_SEARCH_WORD
import com.pixabay.utils.base.BaseViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo
) :
    BaseViewModel() {

    val results = mainRepo.results

    init {
        search(DEFAULT_SEARCH_WORD)
    }

    fun search(word: String) {
        mainRepo.search(word)
    }
}