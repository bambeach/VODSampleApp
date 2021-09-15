package com.bjc.vodsampleapp.api

import com.bjc.vodsampleapp.data.VodListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Pac12DataClient {

    companion object {
        private const val BASE_URL = "https://api.pac-12.com/v3/"

        fun create(): Pac12DataClient {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Pac12DataClient::class.java)
        }
    }

    @GET("vod")
    suspend fun getVodList(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): VodListResponse
}