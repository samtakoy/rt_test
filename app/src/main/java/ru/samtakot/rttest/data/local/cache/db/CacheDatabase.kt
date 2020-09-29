package ru.samtakot.rttest.data.local.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CacheDatabase : RoomDatabase(){

    abstract fun usersDao(): UsersDao

}