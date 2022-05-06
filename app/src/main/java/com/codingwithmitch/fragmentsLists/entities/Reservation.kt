package com.codingwithmitch.fragmentsLists.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Reservation_table")
data class Reservation(@PrimaryKey(autoGenerate = true)
                    var idReservation: Int,
                       var desc: String
                    )
{
    constructor():this(0,"")
}

