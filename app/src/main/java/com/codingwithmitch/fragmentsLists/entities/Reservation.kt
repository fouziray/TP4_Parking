package com.codingwithmitch.fragmentsLists.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "Reservation_table")
data class Reservation(@PrimaryKey(autoGenerate = true)
                    var idReservation: Int,
                       var desc: String,
                       var price: Double,
                       var date: Date
                    )
{
    constructor():this(0,"",0.0, Date())
}

