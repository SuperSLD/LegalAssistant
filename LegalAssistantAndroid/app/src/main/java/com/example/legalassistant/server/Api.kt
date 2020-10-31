package com.example.legalassistant.server

import com.example.legalassistant.models.server.DataWrapper
import com.example.legalassistant.models.server.SolutionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("list")
    fun getList() : Single<DataWrapper<MutableList<SolutionResponse>>>

    @Headers("Content-Type: application/json")
    @GET("item")
    fun getItem(@Path("id") id: String)
}