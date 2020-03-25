package com.sandoval.funnyanimals

import android.app.Application
import com.sandoval.funnyanimals.di.modules.PrefsModule
import com.sandoval.funnyanimals.util.SharedPreferencesHelper

class PrefModuleTest(private val mockPrefs: SharedPreferencesHelper) : PrefsModule() {

    override fun provideSharedPreferencesHelper(app: Application): SharedPreferencesHelper {
        return mockPrefs
    }

}