package com.project.app.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ServiceModule {
    //val baseUrl="https://api.github.com/search/"//repositories?q=newyork"
    //http://clasesderecho.com/api/Values/Get?type=1
    val baseUrl="http://clasesderecho.com/api/"//Values/Get?type=1"
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var moshi: Moshi
//    private lateinit var logInter: HttpLoggingInterceptor
    @Singleton
    @Provides
    fun getRetrofitServiceInterface(retrofit: Retrofit):ServiceInterface{
        return retrofit.create(ServiceInterface::class.java)
    }
    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit
    {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
//
////        logInter = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
//
//        logInter = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(interceptor)
            .connectionSpecs(
                Arrays.asList(
                    ConnectionSpec.CLEARTEXT,
                ConnectionSpec.MODERN_TLS,
                ConnectionSpec.COMPATIBLE_TLS))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cache(null)
//            .addInterceptor(logInter)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl )
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

    }
}