package com.bjc.vodsampleapp.network

import com.bjc.vodsampleapp.data.VodItem
import okhttp3.*
import java.io.IOException

class ApiNetworkClient {

    private val client = OkHttpClient()

    fun getVodList() : List<VodItem> {
        val request = Request.Builder()
            .url("http://api.pac-12.com/v3/vod?")
            .build()

        executeRequest(request)
        return emptyList()
    }

    fun getSchoolsList() {
        val request = Request.Builder()
            .url("http://api.pac-12.com/v3/schools?all=true&pagesize=9999")
            .build()

        executeRequest(request)
    }

    fun getSchoolById(id: String) : String {
        val request = Request.Builder()
            .url("http://api.pac-12.com/v3/schools/$id")
            .build()

        executeRequest(request)
        return ""
    }

    fun getSportsList() {
        val request = Request.Builder()
            .url("http://api.pac-12.com/v3/sports?")
            .build()

        executeRequest(request)
    }

    fun getSportById(id: String) : String {
        val request = Request.Builder()
            .url("http://api.pac-12.com/v3/sports/$id")
            .build()

        executeRequest(request)
        return ""
    }

    private fun executeRequest(request: Request) {
        client.newCall(request)
            .enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) {
                            throw IOException("Error: $response")
                        }
                    }
                }

            })
    }
}