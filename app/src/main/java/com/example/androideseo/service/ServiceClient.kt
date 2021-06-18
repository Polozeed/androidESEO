package com.example.androideseo.service

import android.text.Editable
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.data.models.Client
import com.example.androideseo.data.models.LocalUser
import com.example.androideseo.ui.app.MyApp
import com.google.gson.annotations.SerializedName


/**
 * TODO
 * Classe correspondant au service client
 */
class ServiceClient {

    /**
     * TODO
     *
     * @property mdp
     * @property login
     */
    data class UserInfo(
            @SerializedName("mdp") val mdp: String,
            @SerializedName("login") val login: String
    )

    /**
     * TODO
     *
     * @param user
     * @param mdp
     * @return
     */
    suspend fun connexion(user: Editable, mdp: Editable): Client {
        LocalPreferences.getInstance(MyApp.context!!).deleteToken()
        val userInfo =UserInfo(
                mdp = mdp.toString(),
                login = user.toString()
        )
        val api = ApiService.instance.postconnexion(userInfo)
        return api
    }

    /**
     * TODO
     *
     * @param user
     * @param mdp
     * @return
     */
    suspend fun inscription(user: Editable, mdp: Editable): Client {
        val userInfo =UserInfo(
                mdp = mdp.toString(),
                login = user.toString()
        )
        val api = ApiService.instance.postinscription(userInfo)
        return api
    }

    /**
     * TODO
     *
     * @return
     */
    suspend fun test(): List<Client> {
        val api = ApiService.instance.getListeClient()
        return api
    }

    /**
     * TODO
     *
     * @return
     */
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

    /**
     * TODO
     *
     * @param userId
     * @return
     */
    suspend fun getUser(userId: Int): LocalUser {
        val data = ApiService.instance.getUser(userId)[0]
        return LocalUser(data.id_client, data.login, data.mdp, data.token)
    }

    /**
     * TODO
     *
     * @param userId
     * @return
     */
    suspend fun deleteUser(userId: Int): String {
        val data = ApiService.instance.deleteUser(userId)
        return data
    }

    /**
     * TODO
     *
     * @param userId
     * @param userData
     * @return
     */
    suspend fun editUser(userId: Int, userData: LocalUser): LocalUser {
        val user = UserInfo(userData.mdp,userData.login)
        val data = ApiService.instance.editUser(userId,user)[0]
        System.out.println(data.toString())
        return LocalUser(data.id_client, data.login, data.mdp, data.token)
    }


    companion object {
        val instance = build()

        private fun build(): ServiceClient {
            return ServiceClient()
        }
    }


}