package com.pixabay.repo.repo


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.pixabay.utils.entities.ResponseModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import com.pixabay.utils.models.Success
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepo @Inject
constructor(private val remoteRepo: RemoteRepo, private val dbRepo: DBRepo) {

    val results = MutableLiveData<Response>()


    @SuppressLint("CheckResult")
    fun search(word: String) {
        remoteRepo.searchImages(word)
            .doOnSubscribe { onLoading() }
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onRemoteSuccess(word, it.body())
            }, {
                onRemoteError(word)
            })
    }

    //Save API data to DB, Send results to View Layer
    private fun onRemoteSuccess(word: String, body: ResponseModel?) {
        dbRepo.insertAll(body?.hits?.onEach { it.searchWord = word })
        results.postValue(Success(body?.hits))
    }

    //search DB for results, if remote resource returned error
    @SuppressLint("CheckResult")
    private fun onRemoteError(word: String) {
        dbRepo.search(word)
            .doOnSubscribe { onLoading() }
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    results.postValue(Success(it))
                }, {
                    onRemoteError(word)
                }
            )
    }

    private fun onLoading() {
        results.postValue(Loading(null))
    }


}