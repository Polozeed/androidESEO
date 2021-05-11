package com.example.androideseo.service

import android.text.Editable
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.data.models.Client
import com.example.androideseo.data.models.LocalUser
import com.example.androideseo.ui.MyApp
import com.google.gson.annotations.SerializedName

class ServiceInformation {

    data class EnregInfo(
            @SerializedName("mdp") val mdp: String,
            @SerializedName("login") val login: String

    )


    suspend fun information(user: Editable, mdp: Editable): Client {
        LocalPreferences.getInstance(MyApp.context!!).deleteToken()
        val userInfo =EnregInfo(
                mdp = mdp.toString(),
                login = user.toString()
        )
        val api = ApiService.instance.postconnexion(userInfo)
        return api
    }

    companion object {
        val instance = build()

        private fun build(): ServiceInformation {
            return ServiceInformation()
        }
    }


}
}