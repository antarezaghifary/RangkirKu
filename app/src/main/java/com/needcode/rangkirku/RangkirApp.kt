package com.needcode.rangkirku

import android.app.Application
import timber.log.Timber

class RangkirApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}