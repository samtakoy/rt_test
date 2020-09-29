package ru.samtakot.rttest.data.remote.api.pojo

import com.squareup.moshi.Json
import ru.samtakot.rttest.domain.dto.UserPage


data class ResponsePojo(
    @field:Json(name = "page")
    val page: Int,

    @field:Json(name = "per_page")
    val perPage: Int,

    @field:Json(name = "total")
    val total: Int,

    @field:Json(name = "total_pages")
    val totalPages: Int,

    @field:Json(name = "data")
    val data: List<UserPojo>,

    @field:Json(name = "ad")
    val ad: AdPojo? = null
){

    fun toDomainEntity() = UserPage(page, perPage, total, totalPages, data.map { it.toDomainEntity() })

}