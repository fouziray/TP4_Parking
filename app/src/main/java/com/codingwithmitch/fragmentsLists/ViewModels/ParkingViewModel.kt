package com.codingwithmitch.fragmentsLists.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import android.util.Log
import com.codingwithmitch.fragmentsLists.entities.parkingjdid
import com.codingwithmitch.fragmentsLists.retrofit.ParkingEndpoint

class ParkingViewModel : ViewModel() {
    var parkings = MutableLiveData<List<parkingjdid>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError(throwable.localizedMessage)
    }
    fun getParkings() {
        if (parkings.value == null) {
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = ParkingEndpoint.createInstance().getParkings()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        loading.value = false
                        parkings.postValue(response.body())
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