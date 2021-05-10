package com.example.androideseo.service

import android.text.Editable
import com.example.androideseo.data.models.Client

class ServiceClient {


   suspend fun connexion(user: Editable, mdp: Editable): Client {
       val api = ApiService.instance.postconnexion(user,mdp)
       val  user1 =  api.user
       val password1 = api.password
       val token1 = api.token
       val client = Client(user1,password1,token1)
       return client

   }

    companion object {
        val instance = build()

        private fun build(): ServiceClient {
            return ServiceClient()
        }
    }


}