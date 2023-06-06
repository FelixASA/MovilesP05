package com.example.movilesp05

import android.app.Application

class BaseDeDatosApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}