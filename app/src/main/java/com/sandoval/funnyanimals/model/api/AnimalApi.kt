package com.sandoval.funnyanimals.model.api

import com.sandoval.funnyanimals.model.Animal
import com.sandoval.funnyanimals.model.Key
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AnimalApi {
    @GET("getKey")
    fun getApiKey(): Single<Key>

    @FormUrlEncoded
    @POST("getAnimals")
    fun getAnimals(@Field("key") key: String): Single<List<Animal>>
}