package com.sandoval.funnyanimals.model.service

import com.sandoval.funnyanimals.model.Animal
import com.sandoval.funnyanimals.model.Key
import com.sandoval.funnyanimals.model.api.AnimalApi
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AnimalApiService {
    private val BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net/"

    private val apiAnimalService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // JSON A Objects
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Objects to Observables
        .build()
        .create(AnimalApi::class.java)

    fun getApiKey(): Single<Key> {
        return apiAnimalService.getApiKey()
    }

    fun getAnimals(key: String): Single<List<Animal>> {
        return apiAnimalService.getAnimals(key)
    }
}