package com.codingwithmitch.fragmentsLists.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingwithmitch.fragmentsLists.DAO.ReservationDao
import com.codingwithmitch.fragmentsLists.InsertActivity

import com.codingwithmitch.fragmentsLists.ViewTransactionFragment
import com.codingwithmitch.fragmentsLists.entities.Reservation


@Database(entities = [Reservation::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun ReservationDao(): ReservationDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MyDatabase::class.java, "MyDatabaseName" // Database Name
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

}
}