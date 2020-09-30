package ru.samtakot.rttest.app.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.samtakot.rttest.domain.TimestampHolder
import ru.samtakot.rttest.domain.TimestampHolderImpl
import ru.samtakot.rttest.domain.cache.CacheModel
import ru.samtakot.rttest.domain.cache.CacheModelImpl
import ru.samtakot.rttest.domain.cache.CacheSettings
import javax.inject.Singleton

@Module(includes = [DataModule::class])
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindTimestampHolder(arg: TimestampHolderImpl): TimestampHolder

    @Binds
    @Singleton
    abstract fun bindCacheModel(arg: CacheModelImpl): CacheModel

    @Module
    companion object{

        @Provides
        @Singleton
        fun provideCacheSettings() = CacheSettings(60*60*24)
    }
}