package ru.samtakot.rttest.domain.cache

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import ru.samtakot.rttest.domain.entity.User

interface CacheModel {

    fun init()
    fun clear()
    fun observeNetworkBusyStatus(): Observable<Boolean>
    fun observeErrors(): Observable<CacheError>
    fun observeUsers(): Flowable<List<User>>
    fun retrieveMoreEmployees()

}