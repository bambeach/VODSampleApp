package com.bjc.vodsampleapp.data

import com.bjc.vodsampleapp.api.Pac12DataClient
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

    suspend fun getSchoolNames(client: Pac12DataClient): String {
//        val schoolNames = ""
//        for (school in schools) {
//            val schoolName = schoolList[school.id]
//        }
        return if (schools.size > 1) {
            val schoolIds = schools.joinToString(separator = ";", transform = {school -> school.id.toString() })
            val schoolsResponse = client.getSchoolsByIds(schoolIds)
            val schoolNames = schoolsResponse.schools
            schoolNames.joinToString(separator = ", ", transform = {schoolName -> schoolName.name })
        } else {
            val schoolResponse = client.getSchoolById(schools[0].id)
            schoolResponse.name
        }
    }

    suspend fun getSportsNames(client: Pac12DataClient): String {
        val sportNames = ArrayList<String>()
        for (sport in sports) {
            sportNames.add(client.getSportById(sport.id).name)
        }

        return sportNames.joinToString(separator = ", ")
    }

    fun formatDuration() = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration), TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)))
}