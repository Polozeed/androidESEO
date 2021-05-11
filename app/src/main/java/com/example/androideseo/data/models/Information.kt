package com.example.androideseo.data.models

data class Information(
    var luminosite: Long,
    var proximite: Long,
    var gravite: Long,
    var acceleration: Long
){
    fun identity(): String {
        return "$luminosite"
    }
}