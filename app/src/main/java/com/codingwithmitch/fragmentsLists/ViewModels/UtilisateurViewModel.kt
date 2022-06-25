package com.codingwithmitch.fragmentsLists.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithmitch.fragmentsLists.entities.Utilisateur
import com.codingwithmitch.fragmentsLists.retrofit.ParkingEndpoint
import com.codingwithmitch.fragmentsLists.retrofit.UtilisateurEndpoint
import kotlinx.coroutines.*
import retrofit2.http.FieldMap

class UtilisateurViewModel:ViewModel() {
    var utilisateurs=MutableLiveData<List<Utilisateur>>()
     var user= MutableLiveData<Utilisateur>()
    var loading=MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val registerStatus=MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError(throwable.localizedMessage)
    }
    fun connexionUtilisateurEmail(@FieldMap data:Map<String,String>) {
        loading.value=true
        if (utilisateurs.value == null  || utilisateurs.value!!.isEmpty()) {
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = UtilisateurEndpoint.createInstance().connexionUtilisateurEmail(data)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        loading.value = false
                        utilisateurs.postValue(response.body())
                    } else {
                        onError(response.message())
                    }
                }
            }
        }
    }
    fun getuser() {
        if (user.value == null) {
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = UtilisateurEndpoint.createInstance().getUtilisateurByEmail()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        loading.value = false
                        user.postValue(response.body())
                        println("55555555555555555555555555555555555555555")

                        Log.d("La reponse", response.body().toString())

                    } else {
                        println("tttttttttttttttttttttttttttttttttttttttttttttt")
                        Log.d("La reponse", response.toString())
                        onError(response.message())
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
}