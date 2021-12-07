package com.project.app.di

import com.project.app.model.RecyclerData
import com.project.app.model.RecyclerDataResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
//import retrofit2.http.Headers
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
//    @Headers("Content-Type: application/json")
    @GET("Values/Get")
    suspend fun getDataFromAPI(
        @Header("Content-Type")contentType: String ,
        @Header("Session")session: String,
        @Query("type")query: String): List<RecyclerData>

}