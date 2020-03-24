package com.sandoval.funnyanimals.di.component

import com.sandoval.funnyanimals.di.modules.ApiModule
import com.sandoval.funnyanimals.model.service.AnimalApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: AnimalApiService)
}