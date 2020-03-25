package com.sandoval.funnyanimals

import com.sandoval.funnyanimals.di.modules.ApiModule
import com.sandoval.funnyanimals.model.service.AnimalApiService

class ApiModuleTest(private val apiMockService: AnimalApiService) : ApiModule() {

    override fun provideAnimalApiService(): AnimalApiService {
        return apiMockService
    }
}