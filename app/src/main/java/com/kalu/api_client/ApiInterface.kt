package com.kalu.api_client

import com.kalu.models.Disaster
import com.kalu.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoints{

    @GET("V1/chiefs")
    fun getChiefs(): Call<List<User>>

    @GET("V1/chiefs/{id}")
    fun getChief(@Path(value = "id") id: String) : Call<User>

    @GET("V4/get-disasters")
    suspend fun getAllDisasters() : List<Disaster>
}
