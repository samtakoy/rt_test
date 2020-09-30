package ru.samtakot.rttest.app.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.samtakot.rttest.data.local.AppPreferencesImpl
import ru.samtakot.rttest.data.local.cache.UserCacheRepositoryImpl
import ru.samtakot.rttest.data.local.cache.db.CacheDatabase
import ru.samtakot.rttest.data.remote.RemoteUserRepositoryImpl
import ru.samtakot.rttest.data.remote.api.RequestApi
import ru.samtakot.rttest.domain.Locals
import ru.samtakot.rttest.domain.reps.RemoteUserRepository
import ru.samtakot.rttest.domain.reps.UserCacheRepository
import javax.inject.Singleton


@Module(includes = [ApiModule::class])
abstract class DataModule(private val context: Context) {

    @Binds
    @Singleton
    abstract fun bindUserRepository(arg: UserCacheRepositoryImpl): UserCacheRepository

    @Binds
    @Singleton
    abstract fun bindRemoteUserRepository(arg: RemoteUserRepositoryImpl): RemoteUserRepository

    @Binds
    @Singleton
    abstract fun provideLocals(arg: AppPreferencesImpl): Locals
}