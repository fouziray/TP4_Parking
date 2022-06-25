package com.codingwithmitch.fragmentsLists

import java.io.Serializable

data class Parking(
    val photo: Int,
    val nom: String,
    val lieu: String,
    val etat: Etat,
    val pourcentage: Int,
    val distance: Int,
    val temps: Int,
    val ouverture: Int,
    val fermeture: Int,
    val tarif: Int
):Serializable

//enum class Etat { fermé, ouvert }
enum class Etat { FERME, OUVERT }
class MyModel {
    companion object {
    fun getInstance(): MyModel {
        var instance: MyModel? = null
        if (instance == null) {
            instance = MyModel()
        }
        return instance!!
    }
}
    var pos = 0
    var list = mutableListOf<Parking>()

}

