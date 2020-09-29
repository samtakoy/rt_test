package ru.samtakot.rttest.data.remote.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.samtakot.rttest.data.remote.api.pojo.ResponsePojo

interface RequestApi {

    //users?page=1
    @GET("users?")
    fun getUsers(@Query("page") pageNum: Int): Observable<ResponsePojo>

}