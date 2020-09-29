package ru.samtakot.rttest.domain.reps

import io.reactivex.Flowable
import ru.samtakot.rttest.domain.entity.User

interface UserCacheRepository {

    fun getUsers(): Flowable<List<User>>
    fun getUsersCount(): Flowable<Int>
    fun getUsersCountFromIdList(idList: List<Int>): Flowable<Int>
    fun addData(users: List<User>)
    fun clearEmployees()

}