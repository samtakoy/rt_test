package ru.samtakot.rttest.app.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.samtakot.rttest.data.local.cache.db.CacheDatabase
import ru.samtakot.rttest.data.remote.api.RequestApi
import ru.samtakot.rttest.data.remote.api.retrofit
import javax.inject.Singleton


@Module
class ApiModule() {

    @Provides
    @Singleton
    fun provideRequestApi(): RequestApi =
        retrofit.create(RequestApi::class.java)


    @Provides
    @Singleton
    fun provideDatabase(context: Context): CacheDatabase =
        Room.databaseBuilder(context, CacheDatabase::class.java, "users_cache").build()

}