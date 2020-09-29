package ru.samtakot.rttest.data.remote.api.pojo

import com.squareup.moshi.Json
import ru.samtakot.rttest.domain.entity.User

class UserPojo(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "first_name")
    val firstName: String,
    @field:Json(name = "last_name")
    val lastName: String,
    @field:Json(name = "avatar")
    val avatar: String
) {

    fun toDomainEntity() = User(id, email, firstName, lastName, avatar)
}