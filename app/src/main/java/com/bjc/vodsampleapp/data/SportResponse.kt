package com.bjc.vodsampleapp.data

import com.google.gson.annotations.SerializedName

data class SportResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("id") val id: Int
) {
}