package com.bjc.vodsampleapp.data

import com.bjc.vodsampleapp.network.ApiNetworkClient
import java.util.concurrent.TimeUnit

data class VodItem(
    val id: String,
    val url: String,
    val title: String,
    val duration: Long,
    val sports: ArrayList<Sport>,
    val schools: ArrayList<School>,
    val images: Images
) {

    fun getSchoolNames(apiNetworkClient: ApiNetworkClient): ArrayList<String> {
        var schoolNames = ArrayList<String>()
        for (school in schools) {
            var schoolName = apiNetworkClient.getSchoolById(school.id)
        }

        return schoolNames
    }

    fun getSportsNames(apiNetworkClient: ApiNetworkClient): ArrayList<String> {
        var sportNames = ArrayList<String>()
        for (sport in sports) {
            var sportName = apiNetworkClient.getSportById(sport.id)
        }

        return sportNames
    }

    fun formatDuration() = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration), TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)))
}