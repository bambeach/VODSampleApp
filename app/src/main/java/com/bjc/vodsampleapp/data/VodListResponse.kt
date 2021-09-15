package com.bjc.vodsampleapp.data

import com.google.gson.annotations.SerializedName

data class VodListResponse(
    @field:SerializedName("programs") val programs: List<VodItem>,
    @field:SerializedName("total_pages") val totalPages: Int
) {
}