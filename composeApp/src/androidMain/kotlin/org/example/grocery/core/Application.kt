package org.example.grocery.core

import android.app.Application


class AndroidApp : Application() {

    companion object {
        lateinit var INSTANCE: AndroidApp
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
//        startKoin {
//            androidContext(this@MainApplication)
//            modules(productModule)
//        }
    }
}