package com.codingwithmitch.fragmentsLists.DAO

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.codingwithmitch.fragmentsLists.entities.Reservation

@Dao
interface ReservationDao {

    @Insert(onConflict = REPLACE)
    fun insert(data: Reservation)

    @Delete
    fun delete(data: Reservation)


    @Query("SELECT * from Reservation_table ORDER BY idReservation ASC")
    fun getAll(): List<Reservation>

//    @Query("SELECT * FROM Reservation_table WHERE id = :id LIMIT 1")
//    fun getNote(id: Int): Reservation

}