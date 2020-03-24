package com.sandoval.funnyanimals.model.service

import com.sandoval.funnyanimals.di.component.DaggerApiComponent
import com.sandoval.funnyanimals.model.Animal
import com.sandoval.funnyanimals.model.Key
import com.sandoval.funnyanimals.model.api.AnimalApi
import io.reactivex.Single
import javax.inject.Inject

class AnimalApiService {

    @Inject
    lateinit var api: AnimalApi

    init {
        DaggerApiComponent.create()
            .inject(this)
    }

    fun getApiKey(): Single<Key> {
        return api.getApiKey()
    }

    fun getAnimals(key: String): Single<List<Animal>> {
        return api.getAnimals(key)
    }
}