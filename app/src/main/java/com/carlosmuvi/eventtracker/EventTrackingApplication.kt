package com.carlosmuvi.eventtracker

import android.app.Application

class EventTrackingApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}

