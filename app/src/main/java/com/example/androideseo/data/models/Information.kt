package com.example.androideseo.data.models

data class Information(
    var id_info: Long,
    var luminosite: Long,
    var proximite: Long,
    var gravite: Long,
    var acceleration: Long
){
    override fun toString(): String {
        return super.toString()
    }

    fun identity(): String {
        return "Id:$id_info, Luminosité:$luminosite, Proximité:$proximite, Autre: $gravite; $acceleration"
    }




    fun getIdString(): String {
        return "Id: " + id_info
    }
    fun getLuminositetring(): String {
        return "Luminosité: " + luminosite.toString()
    }
    fun getProximiteString(): String {
        return "Proximité: " + proximite.toString()
    }
    fun getGraviteString(): String {
        return "Gravité: " + gravite.toString()
    }
    fun getAccelerationString(): String {
        return "Accélération: " + acceleration.toString()
    }
}