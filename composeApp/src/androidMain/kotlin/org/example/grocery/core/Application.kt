package org.example.grocery.core

import android.app.Application
import org.example.grocery.core.di.KoinInitializer


class AndroidApp : Application() {

    companion object {
        lateinit var INSTANCE: AndroidApp
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        KoinInitializer(this.baseContext).init()
    }
}