package com.project.app.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {
    //val baseUrl="https://api.github.com/search/"//repositories?q=newyork"
    //http://clasesderecho.com/api/Values/Get?type=1
    val baseUrl="http://clasesderecho.com/api/"//Values/Get?type=1"
    private lateinit var okHttpClient: OkHttpClient

    @Singleton
    @Provides
    fun getRetrofitServiceInterface(retrofit: Retrofit):ServiceInterface{
        return retrofit.create(ServiceInterface::class.java)
    }
    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit
    {
//        okHttpClient = OkHttpClient.Builder()
////                .addInterceptor(interceptor)
//            .connectionSpecs(
//                Arrays.asList(
//                ConnectionSpec.MODERN_TLS,
//                ConnectionSpec.COMPATIBLE_TLS))
//            .followRedirects(true)
//            .followSslRedirects(true)
//            .retryOnConnectionFailure(true)
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .readTimeout(20, TimeUnit.SECONDS)
//            .writeTimeout(20, TimeUnit.SECONDS)
//            .cache(null)
//            .build()

        return Retrofit.Builder().baseUrl(baseUrl )
            .addConverterFactory(MoshiConverterFactory.create())
//            .client(okHttpClient)
            .build()

    }
}