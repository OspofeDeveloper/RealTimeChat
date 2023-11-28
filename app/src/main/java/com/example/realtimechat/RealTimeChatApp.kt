package com.example.realtimechat

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RealTimeChatApp : Application() {

    /**
     * Inicializamos Firebase aqui, ya que lo primero que abre la aplicación antes de cargar
     * ninguna pantalla es esta clase
     */
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}