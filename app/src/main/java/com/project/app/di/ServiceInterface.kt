package com.project.app.di

import com.project.app.model.RecyclerList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ServiceInterface {
    //    @FormUrlEncoded
//    @Headers("Content-Type: application/json")
//    @POST("Account/Auth")
//    suspend fun login(@Body body: UserRequest) : UserResponse
//
//    //    @FormUrlEncoded
//    @Headers("Content-Type: application/json")
//    @POST("Control/Send")
//    suspend fun control(@Body body: ControlRequest, @Query("token") token: String, @Query("user") user: String): Call<ControlResponse>

    @GET("Values/Get")
    fun getDataFromAPI(@Query("type")query: String): Call<RecyclerList>?

}