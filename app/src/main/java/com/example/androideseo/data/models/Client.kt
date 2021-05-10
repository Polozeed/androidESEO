package com.example.androideseo.data.models

data class Client (

    var user: String,
    var password: String,
    var token: String


){
    fun identity(): String {
        return "$user $password"
    }
}