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
data class paginatedParkings(
       var data : List<parkingjdid>,
       var count: Number,
       var total: Number,
       var page: Number,
       var pageCount : Number,
)
data class parkingjdid(
    var idParking: Number,
    var nomParking: String,
    var adresseParking: String,
    var communeParking: String,
    var etatParking: String,
    var nbPlacesParking: Number,
    var nbPlacesDisponiblesParking: Number,
    var tarifParHeure: Number,
    var longitudeParking: Number,
    var latitudeParking: Number,
    var horairesOuverturesFermetures: List<horaireOuverture> ,
)

data class horaireOuverture (
    var idHorairesOuvertureFermeture: Number,
    var jours: String,
    var heureOuverture : String,
    var heureFermeture : String,
)
// the one where heroku used to go with
//data class parkingjdid(
//    var idParking: Number,
//    var photoParking: String,
//    var nomParking: String,
//    var adrParking: String,
//    var etatParking: Etat,
//    var tauxOccupation: Number,
//    var tarifParHeure: Number,
//    var parkingPosLatitude: Number,
//    var parkingPosLongitude: Number,
//)