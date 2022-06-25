package com.codingwithmitch.fragmentsLists.retrofit
import com.codingwithmitch.fragmentsLists.entities.parkingjdid
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ParkingEndpoint {
    @GET("parking/")
    suspend fun getParkings(): Response<List<parkingjdid>>
    companion object {
        @Volatile
        var parkingEndpoint: ParkingEndpoint? = null
        fun createInstance(): ParkingEndpoint{
        if(parkingEndpoint == null){
            synchronized(this){

                if(parkingEndpoint == null){
                    println("_-_-_-_-_-_-_-_-_-__-_-___-_-_-_-_-_-_-")
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://db-projet-tdm.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    parkingEndpoint = retrofit.create(ParkingEndpoint::class.java)
                }
            }
        }
            return parkingEndpoint!!
        }
    }
}