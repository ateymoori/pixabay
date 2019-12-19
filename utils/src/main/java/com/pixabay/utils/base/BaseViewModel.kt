package com.pixabay.utils.base

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val repoLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

//    private var mNavigator: WeakReference<N?>? = null
//
//    var navigator: N?
//        get() = mNavigator?.get()
//        set(navigator) {
//            this.mNavigator = WeakReference(navigator)
//        }

    open fun onViewCreated() {

    }

    open fun onViewResumed() {

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}