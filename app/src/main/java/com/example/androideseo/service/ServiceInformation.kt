package com.example.androideseo.service

import com.example.androideseo.data.models.Information
import com.google.gson.annotations.SerializedName

class ServiceInformation {

    data class EnregInfo(
            @SerializedName("luminosite") val luminosite: Long,
            @SerializedName("proximite") val proximite: Long,
            @SerializedName("gravite") val gravite: Long,
            @SerializedName("acceleration") val acceleration: Long
    )


    suspend fun enregInfo(enregInfo: EnregInfo): Information {
        val api = ApiService.instance.postinformation(enregInfo)
        return api
    }

    suspend fun afficheInfos(): List<Information> {
        val api = ApiService.instance.getInfos()

        return api
    }

    suspend fun afficheUneInfo(id_info: Int): Information {
        val data = ApiService.instance.getUneInfo(id_info)[0]
        return data
    }

    companion object {
        val instance = build()

        private fun build(): ServiceInformation {
            return ServiceInformation()
        }
    }


}