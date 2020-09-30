package ru.samtakot.rttest.domain.reps

import io.reactivex.Completable
import io.reactivex.Flowable
import ru.samtakot.rttest.domain.entity.User

interface UserCacheRepository {

    fun getUsers(): Flowable<List<User>>
    fun getUser(userId: Int): Flowable<User>
    fun getUsersCount(): Flowable<Int>
    fun getUsersCountFromIdList(idList: List<Int>): Flowable<Int>
    fun addData(users: List<User>): Completable
    fun clearUsers(): Completable

}