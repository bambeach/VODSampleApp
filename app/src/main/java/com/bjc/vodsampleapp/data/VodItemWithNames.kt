package com.bjc.vodsampleapp.data

import java.util.concurrent.TimeUnit

class VodItemWithNames(
    val id: String,
    val url: String,
    val title: String,
    private val duration: Long,
    val sports: String,
    val schools: String,
    val images: Images
) {
    fun formatDuration() = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration), TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)))
}