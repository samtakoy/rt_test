package ru.samtakot.rttest.data.local.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import ru.samtakot.rttest.data.local.cache.db.CacheDatabase
import ru.samtakot.rttest.data.local.cache.db.UserEntity
import ru.samtakot.rttest.domain.entity.User
import ru.samtakot.rttest.domain.reps.UserCacheRepository
import javax.inject.Inject

class UserCacheRepositoryImpl @Inject constructor(
    val db: CacheDatabase
): UserCacheRepository {


    override fun getUsers(): Flowable<List<User>>  =
        db.usersDao().getAllUsers()
            .map {list -> list.map { it.toDomainEntity()} }

    override fun getUsersCount(): Flowable<Int> =
        db.usersDao().getUserCount()

    override fun getUsersCountFromIdList(idList: List<Int>): Flowable<Int> =
        db.usersDao().getUserCountFromIdList(idList)

    override fun addData(users: List<User>): Completable =
        Completable.fromCallable {
            db.usersDao().addUsers(users.map { UserEntity.fromDomainEntity(it) })
            return@fromCallable true
        }


    override fun clearUsers(): Completable =
        Completable.fromCallable {
            db.usersDao().clearUsers()
            return@fromCallable true
        }
}