package com.example.androideseo.service

import android.text.Editable
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.data.models.Client
import com.example.androideseo.data.models.LocalUser
import com.example.androideseo.ui.app.MainActivity
import com.example.androideseo.ui.app.MyApp
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ServiceClient {

    data class UserInfo(
            @SerializedName("mdp") val mdp: String,
            @SerializedName("login") val login: String
    )


    suspend fun connexion(user: Editable, mdp: Editable): Client {
        LocalPreferences.getInstance(MyApp.context!!).deleteToken()
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


    suspend fun getUsers(): List<LocalUser> {
        return ApiService.instance.getUsers().map {
            LocalUser(
                    it.id_client,
                    it.login,
                    it.mdp,
                    it.token
            )
        }
    }

    suspend fun getUser(userId: Int): LocalUser {
        val data = ApiService.instance.getUser(userId)[0]
        return LocalUser(data.id_client, data.login, data.mdp, data.token)
    }

    suspend fun deleteUser(userId: Int): String {
        val data = ApiService.instance.deleteUser(userId)
        return data
    }

    suspend fun editUser(userId: Int): LocalUser {
        val data = ApiService.instance.editUser(userId)[0]
        return LocalUser(data.id_client, data.login, data.mdp, data.token)
    }





    companion object {
        val instance = build()

        private fun build(): ServiceClient {
            return ServiceClient()
        }
    }


}