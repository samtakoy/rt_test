package ru.samtakot.rttest.data.local.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flowable<List<UserEntity>>

    @Query("SELECT count(*) FROM users")
    fun getUserCount(): Flowable<Int>

    @Query("SELECT count(*) FROM users WHERE id in (:idList)")
    fun getUserCountFromIdList(idList: List<Int>): Flowable<Int>

    @Insert
    fun addUsers(employees: List<UserEntity>)

    @Query("DELETE FROM users")
    fun clearUsers()

}