package com.example.pfm

import android.app.Application
import com.example.pfm.di.AppComponent
import com.example.pfm.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
       appComponent = DaggerAppComponent.builder().applicationContext(this).build()
    }

}