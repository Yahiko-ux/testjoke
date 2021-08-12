package com.testjoke.data.remote

import com.testjoke.data.entities.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("jokes/random/{count}?firstName=Chuck&lastName=Norris")
    suspend fun getJokes(@Path("count") count: Int): Response<ApiResponse>
}