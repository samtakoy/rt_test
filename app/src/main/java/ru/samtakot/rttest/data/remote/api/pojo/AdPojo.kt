package ru.samtakot.rttest.data.remote.api.pojo

import com.squareup.moshi.Json

class AdPojo(

    @field:Json(name = "company")
    val company: String? = null,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "text")
    val text: String? = null
)