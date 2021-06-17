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

    fun identity2(): String {
        val NEWLINE = System.getProperty("line.separator")
        return "Id:" + id_info +","+  NEWLINE +"Luminosité: \n" + luminosite +", \n Proximité:$proximite, Autre: $gravite; $acceleration"
    }
}