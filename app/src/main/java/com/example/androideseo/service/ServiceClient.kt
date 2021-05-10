package com.example.androideseo.service

import android.text.Editable
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.data.models.Client
import com.google.gson.annotations.SerializedName

class ServiceClient {

    data class UserInfo (
            @SerializedName("mdp") val mdp: String,
            @SerializedName("login") val login: String

    )


   suspend fun connexion(user: Editable, mdp: Editable): Client {
       val userInfo =UserInfo(
               mdp = mdp.toString(),
               login = user.toString()
                )
       val api = ApiService.instance.postconnexion(userInfo)

       return api


   }

    companion object {
        val instance = build()

        private fun build(): ServiceClient {
            return ServiceClient()
        }
    }


}