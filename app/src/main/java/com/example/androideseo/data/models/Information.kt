package com.example.androideseo.data.models

data class Information(
        var id_info : Long,
        var luminosite: Long,
        var proximite: Long,
        var gravite: Long,
        var acceleration: Long
){
    override fun toString(): String {
        return super.toString()
    }

    fun identity(): String {
        return "$id_info $luminosite $proximite $gravite $acceleration"
    }
}