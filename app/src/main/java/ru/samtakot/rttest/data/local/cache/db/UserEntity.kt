package ru.samtakot.rttest.data.local.cache.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.samtakot.rttest.domain.entity.User

@Entity(
    tableName = "users"
)
class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastName: String,
    @ColumnInfo(name = "avatar")
    var avatar: String
) {

    companion object{
        fun fromDomainEntity(user: User) = UserEntity(
            user.id, user.email, user.firstName, user.lastName, user.avatar
        )
    }

    fun toDomainEntity() = User(id, email, firstName, lastName, avatar)

}