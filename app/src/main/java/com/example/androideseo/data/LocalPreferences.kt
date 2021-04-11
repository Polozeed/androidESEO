package com.example.androideseo.data

import android.content.Context
import android.content.SharedPreferences
import android.text.method.TextKeyListener.clear
import android.widget.Toast

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

    // Fct permettant d'ajouter une adresse dans lieu de sauvegarde " histories"
    fun addToHistory(newEntry: String){
        val history = this.getHistory()
        history?.add(newEntry)
        sharedPreferences.edit().putStringSet("histories", history).apply()
    }

    // Fct permettant de recupérer les adresses sauvegardés
    fun getHistory(): MutableSet<String>? {
        return sharedPreferences.getStringSet("histories",  mutableSetOf<String>().toMutableSet() )
    }

    // Fct permettant de supprimer les adresses
    fun deleteAllHistory() {
        sharedPreferences.edit().clear().apply()
    }

    // Fct permettant de verifier si le lieu de sauvegarde est vide
    fun nullHistory(): Int {
        var res = sharedPreferences.getStringSet("histories", mutableSetOf<String>())?.isEmpty();
        if (res != true) {
            return 1
        } else
            return 0
    }
}




