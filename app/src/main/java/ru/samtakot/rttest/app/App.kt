package ru.samtakot.rttest.app

import androidx.multidex.MultiDexApplication

import ru.samtakot.rttest.app.di.DaggerAppComponent
import ru.samtakot.rttest.app.di.Di


class App : MultiDexApplication(){



    override fun onCreate() {
        super.onCreate()

        Di.appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build()
    }
}