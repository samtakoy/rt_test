package ru.samtakot.rttest.extensions

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

public fun <T> Observable<T>.ioToMain(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


public fun <T> Flowable<T>.ioToMain(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

public fun Completable.ioToMain(): Completable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


public fun <T> Observable<T>.io(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}


public fun <T> Flowable<T>.io(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
}

public fun Completable.io(): Completable {
    return this.subscribeOn(Schedulers.io())
}