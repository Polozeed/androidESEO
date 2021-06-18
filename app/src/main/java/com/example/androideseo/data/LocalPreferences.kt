package com.example.androideseo.data

import android.content.Context
import android.content.SharedPreferences

class LocalPreferences private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    companion object {
        private var INSTANCE: LocalPreferences? = null

        fun getInstance(context: Context): LocalPreferences {
            return INSTANCE?.let {
                INSTANCE
            } ?: run {
                INSTANCE = LocalPreferences(context)
                return INSTANCE!!
            }
        }


    }

    /**
     * TODO
     *
     * @param newEntry
     */
    fun addTokenToHistory(newEntry: String){
        sharedPreferences.edit().putString("token",newEntry).apply()
    }


    /**
     * TODO
     *
     * @return
     */
    fun getToken(): String? {
        return sharedPreferences.getString("token", String())
    }


    /**
     * TODO
     *
     */
    fun deleteToken() {
        sharedPreferences.edit().putString("token",null).apply()
    }

    /**
     * TODO
     *
     * @return
     */
    fun nullToken(): Int {
        var res = sharedPreferences.getString("token",String())?.isEmpty();
        if (res != true) {
            return 1
        } else
            return 0
    }


    /**
     * TODO Fct permettant d'ajouter une adresse dans lieu de sauvegarde " histories"
     *
     * @param newEntry
     */

    fun addToHistory(newEntry: String){
        val history = this.getHistory()
        history?.add(newEntry)
        sharedPreferences.edit().putStringSet("histories", history).apply()
    }


    /**
     * TODO Fct permettant de recupérer les adresses sauvegardés
     *
     * @return
     */
    fun getHistory(): MutableSet<String>? {
        return sharedPreferences.getStringSet("histories",  mutableSetOf<String>().toMutableSet() )
    }


    /**
     * TODO fct permettant de supprimer les adresses
     *
     */
    fun deleteAllHistory() {
        sharedPreferences.edit().clear().apply()
    }


    /**
     * TODO Fct permettant de verifier si le lieu de sauvegarde est vide
     *
     * @return
     */
    fun nullHistory(): Int {
        var res = sharedPreferences.getStringSet("histories", mutableSetOf<String>())?.isEmpty();
        if (res != true) {
            return 1
        } else
            return 0
    }
}




