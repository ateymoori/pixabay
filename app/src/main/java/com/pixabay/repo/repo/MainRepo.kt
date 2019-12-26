package com.pixabay.repo.repo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pixabay.utils.entities.ImageModel
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Response
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class MainRepo @Inject
constructor(private val remoteRepo: RemoteRepo, private val dbRepo: DBRepo) {

    val results = MutableLiveData<Response>()

    suspend fun newSearch(word: String): List<ImageModel> {
        return try {
            val remoteResults = remoteRepo.searchImages(word).hits
            cacheRemoteResults(word, remoteResults)
            remoteResults
        } catch (e: UnknownHostException) {
            dbRepo.search(word)
        } catch (e: Exception) {
            Log.d("co__", e.toString())
            listOf()
        }
    }

    @SuppressLint("CheckResult")
    fun search(word: String) {
//        remoteRepo.searchImages(word)
//            .doOnSubscribe { onLoading() }
//            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                onRemoteSuccess(word, it.body())
//            }, {
//                onRemoteError(word)
//            })
    }

    //Save API data to DB, Send results to View Layer
    private fun cacheRemoteResults(word: String, images: List<ImageModel>) {
        dbRepo.insertAll(images.onEach { it.searchWord = word })
        //results.postValue(Success(body?.hits))
    }

    //search DB for results, if remote resource returned error
    @SuppressLint("CheckResult")
    private fun onRemoteError(word: String) {
//        dbRepo.search(word)
//            .doOnSubscribe { onLoading() }
//            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    results.postValue(Success(it))
//                }, {
//                    onRemoteError(word)
//                }
//            )
    }

    private fun onLoading() {
        results.postValue(Loading(null))
    }

}