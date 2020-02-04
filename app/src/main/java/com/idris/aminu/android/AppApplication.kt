package com.idris.aminu.android

import android.app.Application
import android.os.Bundle
import timber.log.Timber


class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}