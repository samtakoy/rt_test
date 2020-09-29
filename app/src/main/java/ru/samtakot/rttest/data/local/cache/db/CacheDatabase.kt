package ru.samtakot.rttest.data.local.cache.db

import androidx.room.Database

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class CacheDatabase {

    abstract fun usersDao(): UsersDao

}