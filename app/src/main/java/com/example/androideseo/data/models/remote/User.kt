package com.example.androideseo.data.models.remote

data class User(
        var id_client: Int,
        var login: String,
        var mdp: String,
        var token: String
)