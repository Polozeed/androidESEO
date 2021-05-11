package com.example.androideseo.data.models

data class LocalSac (

    var id: Int,
    var long: String,
    var lat: String
    ){

    fun identity(): String {
        return "$long $lat"
    }

}