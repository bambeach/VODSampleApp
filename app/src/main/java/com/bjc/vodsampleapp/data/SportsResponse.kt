package com.bjc.vodsampleapp.data

import com.google.gson.annotations.SerializedName

data class SportsResponse(
    @field:SerializedName("sports") val sports: List<SportName>
) {}