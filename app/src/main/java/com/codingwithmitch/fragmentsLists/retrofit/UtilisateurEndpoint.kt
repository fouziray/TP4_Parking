package com.codingwithmitch.fragmentsLists.retrofit

import com.codingwithmitch.fragmentsLists.ViewModels.ParkingViewModel
import com.codingwithmitch.fragmentsLists.entities.Utilisateur
import com.codingwithmitch.fragmentsLists.entities.userTokenInfos
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UtilisateurEndpoint {
    @FormUrlEncoded
    @POST("/utilisateur/connexion_Email/")
    suspend fun connexionUtilisateurEmail(@FieldMap data:Map<String,String>): Response<List<Utilisateur>>
    @POST("/auth/signin")
    suspend fun authentifier(): Response<userTokenInfos>

    @POST("utilisateur/")
    suspend fun getUtilisateurByEmail(): Response<Utilisateur>
    companion object{
        @Volatile
        var utilisateurEndpoint:UtilisateurEndpoint?=null
        fun createInstance():UtilisateurEndpoint{
            if(utilisateurEndpoint==null){
                synchronized(this){
                    utilisateurEndpoint=
                        Retrofit.Builder().baseUrl("https://f539-154-121-25-179.ngrok.io/").addConverterFactory(GsonConverterFactory.create()).build().create(UtilisateurEndpoint::class.java)
                }
            }
            return utilisateurEndpoint!!
        }
    }



}