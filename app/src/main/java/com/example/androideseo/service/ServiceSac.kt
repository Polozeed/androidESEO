package com.example.androideseo.service

import com.example.androideseo.data.models.LocalUser
import com.google.gson.annotations.SerializedName

class ServiceSac {

    data class sacInfo(
        @SerializedName("geo_point_2d") val point: Point,
        @SerializedName("datasetid") val login: String

    )

    data class Point(
        var long: String,
        var lat: String
    )


    suspend fun getSac(): List<LocalUser> {
        return ApiAngersService.instance.getUsers().map {
            LocalUser(
                it.id_client,
                it.login,
                it.mdp,
                it.token
            )
        }
    }




    companion object {
        val instance = build()

        private fun build(): ServiceClient {
            return ServiceClient()
        }
    }
}