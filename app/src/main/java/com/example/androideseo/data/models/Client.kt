package com.example.androideseo.data.models

data class Client (

    var user: String,
    var password: String,
    var token: String


){

    override fun toString(): String {
        return super.toString()
    }

    fun identity(): String {
        return "$user $password $token"
    }
}