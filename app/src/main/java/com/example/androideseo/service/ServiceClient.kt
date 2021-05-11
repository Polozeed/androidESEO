package com.example.androideseo.service

import android.text.Editable
import android.util.Patterns
import com.example.androideseo.data.models.Client
import com.google.gson.annotations.SerializedName


class ServiceClient {

    data class UserInfo(
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

    suspend fun inscription(user: Editable, mdp: Editable): Client {
        val userInfo =UserInfo(
                mdp = mdp.toString(),
                login = user.toString()
        )
        val api = ApiService.instance.postinscription(userInfo)
        return api
    }


    suspend fun test(): List<Client> {
        val api = ApiService.instance.getListeClient()
        System.out.println("----------------------------------" + api[0].identity())
        return api
    }



    companion object {
        val instance = build()

        private fun build(): ServiceClient {
            return ServiceClient()
        }
    }


}