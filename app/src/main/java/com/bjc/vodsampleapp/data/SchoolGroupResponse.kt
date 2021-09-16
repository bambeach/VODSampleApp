package com.bjc.vodsampleapp.data

import com.google.gson.annotations.SerializedName

data class SchoolGroupResponse(
    @field:SerializedName("schools") val schools: List<SchoolName>
) {
}