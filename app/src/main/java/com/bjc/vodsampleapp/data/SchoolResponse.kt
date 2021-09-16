package com.bjc.vodsampleapp.data

import com.google.gson.annotations.SerializedName


data class SchoolResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String
) { }