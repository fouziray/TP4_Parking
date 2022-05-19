package com.codingwithmitch.fragmentsLists.DAO

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.codingwithmitch.fragmentsLists.entities.Reservation
import java.util.*

@Dao
interface ReservationDao {

    @Insert(onConflict = REPLACE)
    fun insert(data: Reservation)

    @Delete
    fun delete(data: Reservation)


    @Query("SELECT * from Reservation_table ORDER BY idReservation ASC")
    fun getAll(): List<Reservation>

    @Query("SELECT * FROM Reservation_table WHERE date < :today AND date > :yesterday")
    fun getDateEnCours(today: Date, yesterday: Date): List<Reservation>

    @Query("SELECT * FROM Reservation_table WHERE `desc` = :desc")
    fun getReservationByDesc(desc: String): Reservation

}