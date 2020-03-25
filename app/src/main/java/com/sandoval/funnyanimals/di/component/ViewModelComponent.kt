package com.sandoval.funnyanimals.di.component

import com.sandoval.funnyanimals.di.modules.ApiModule
import com.sandoval.funnyanimals.di.modules.AppModule
import com.sandoval.funnyanimals.di.modules.PrefsModule
import com.sandoval.funnyanimals.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, PrefsModule::class, AppModule::class])
interface ViewModelComponent {
    fun inject(viewModel: ListViewModel)
}