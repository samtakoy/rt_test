package ru.samtakot.rttest.domain.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import ru.samtakot.rttest.domain.entity.User

interface CacheModel {

    fun checkForInitialization()
    fun observeNetworkBusyStatus(): Observable<Boolean>
    fun observeErrors(): Observable<CacheError>
    fun observeUsers(): Flowable<List<User>>
    fun retrieveMoreEmployees()

    fun invalidateDbCache(): Completable
    fun clearDbCache(): Completable
}