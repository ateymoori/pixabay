package com.pixabay.utils.base

import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {


    val compositeDisposable: CompositeDisposable = CompositeDisposable()



    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}