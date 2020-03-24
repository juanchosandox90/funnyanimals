package com.sandoval.funnyanimals.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sandoval.funnyanimals.model.Animal
import com.sandoval.funnyanimals.model.Key
import com.sandoval.funnyanimals.model.service.AnimalApiService
import com.sandoval.funnyanimals.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val apiService = AnimalApiService()

    private val prefs = SharedPreferencesHelper(getApplication())
    private var invalidApiKey: Boolean = false

    fun refresh() {
        loading.value = true
        invalidApiKey = false
        val key = prefs.getApiKey()
        if (key.isNullOrEmpty()) {
            getKey()
        } else {
            getAnimals(key)
        }
    }

    fun hardRefresh(){
        loading.value = true
        getKey()
    }

    private fun getKey() {
        disposable.add(
            apiService.getApiKey()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Key>() {
                    override fun onSuccess(key: Key) {
                        if (key.key.isNullOrEmpty()) {
                            loadError.value = true
                            loading.value = false
                        } else {
                            prefs.saveApiKey(key.key)
                            getAnimals(key.key)
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (!invalidApiKey) {
                            invalidApiKey = true
                            getKey()
                        } else {
                            e.printStackTrace()
                            loading.value = false
                            loadError.value = true
                        }
                    }

                })
        )
    }

    private fun getAnimals(key: String) {
        disposable.add(
            apiService.getAnimals(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Animal>>() {
                    override fun onSuccess(animalList: List<Animal>) {
                        loading.value = false
                        loadError.value = false
                        animals.value = animalList
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                        animals.value = null
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}