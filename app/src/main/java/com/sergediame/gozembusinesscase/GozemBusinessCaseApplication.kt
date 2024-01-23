package com.sergediame.gozembusinesscase

import android.app.Application
import networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class GozemBusinessCaseApplication:Application() {


        override fun onCreate() {
            super.onCreate()

            startKoin {
                // Log Koin into Android logger
                androidLogger()
                // Reference Android context
                androidContext(this@GozemBusinessCaseApplication)
                // Load modules
                modules(appModule, networkModule)
            }

        }

}