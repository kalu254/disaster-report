package com.kalu.network

import com.kalu.models.Disaster
import com.kalu.models.UserAuthorization
import com.kalu.models.LoginResponse
import com.kalu.models.User
import retrofit2.Call
import retrofit2.http.*

interface UserAuthApi {

    @POST("auth/signin")
    suspend fun login(@Body userAuthorization: UserAuthorization) : LoginResponse
    @POST("auth/signup")
    suspend fun register(@Body user: User) : LoginResponse
}

interface EndPoints{

    @GET("api/V1/chiefs")
    fun getChiefs(): Call<List<User>>

    @GET("api/V1/chiefs/{id}")
    fun getChief(@Path(value = "id") id: String): Call<User>

    @GET("api/V4/get-disasters")
    suspend fun getAllDisasters(): List<Disaster>


}
