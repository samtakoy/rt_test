package ru.samtakot.rttest.domain.dto

import ru.samtakot.rttest.data.remote.api.pojo.UserPojo
import ru.samtakot.rttest.domain.entity.User

class UserPage(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val users: List<User>
) {
    fun isLast(): Boolean = page == totalPages

    val userIds: List<Int>
        get() = users.map { it.id }

}