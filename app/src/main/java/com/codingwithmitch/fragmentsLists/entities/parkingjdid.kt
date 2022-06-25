package com.codingwithmitch.fragmentsLists.entities

import com.codingwithmitch.fragmentsLists.Etat

//data class parkingjdid(
//    var id_parking: Int,
//    var photo: String,
//    var nom: String,
//    var commune: String,
//    var etat: Boolean,
//    var capacite: Int,
//    var nb_places_libres: Int,
//    var latitude: Double,
//    var longitude: Double,
//    var horaire: String,
//    var tarif: Double
//)
data class parkingjdid(
    var idParking: Number,
    var photoParking: String,
    var nomParking: String,
    var adrParking: String,
    var etatParking: Etat,
    var tauxOccupation: Number,
    var tarifParHeure: Number,
    var parkingPosLatitude: Number,
    var parkingPosLongitude: Number,
)