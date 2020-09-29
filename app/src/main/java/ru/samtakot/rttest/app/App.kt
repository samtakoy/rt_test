package ru.samtakot.rttest.app

import android.app.Application
import ru.samtakot.rttest.app.di.DaggerAppComponent
import ru.samtakot.rttest.app.di.Di

class App : Application(){



    override fun onCreate() {
        super.onCreate()

        Di.appComponent = DaggerAppComponent.builder().build()
    }
}