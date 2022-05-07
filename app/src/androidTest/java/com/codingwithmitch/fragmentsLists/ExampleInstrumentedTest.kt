package com.codingwithmitch.fragmentsLists

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.codingwithmitch.fragmentsLists.database.MyDatabase
import com.codingwithmitch.fragmentsLists.entities.Reservation

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.codingwithmitch.navigationcomponentsexample", appContext.packageName)
    }
}
class InstrumentedTest {
    lateinit var mDataBase: MyDatabase

    @Before
    fun initDB() {
        mDataBase =
            Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                MyDatabase::class.java
            ).build()
    }
    @Test
    fun testInsertionReservation() {
        val reservation1 = Reservation(2, "parking test", 20.0, Calendar.getInstance().time)
        mDataBase.ReservationDao().insert(reservation1)
        val resultat=mDataBase.ReservationDao().getReservationByDesc(reservation1.desc)

        assertEquals(reservation1,resultat)
    }
    @Test
    fun testConsultationReservation() {

        val reservation1 = Reservation(2, "parking test", 20.0, Calendar.getInstance().time)
        val reservation2 = Reservation(3, "parking test 2", 20.0, Calendar.getInstance().time)
        mDataBase.ReservationDao().insert(reservation1)
        mDataBase.ReservationDao().insert(reservation2)
        val resultat=mDataBase.ReservationDao().getAll()
        assertArrayEquals(arrayOf(reservation1,reservation2),resultat.toTypedArray())
    }
    @Test
    fun testConsultationReservationToday(){
        val reservation1 = Reservation(2, "parking test", 20.0, Calendar.getInstance().time)
        val reservation2 = Reservation(3, "parking test 2", 20.0, Calendar.getInstance().time)
        val ca=Calendar.getInstance()
        ca.add(Calendar.DATE,-3)
        val yesterday=ca.time
        val reservation3 = Reservation(4, "parking test 3", 20.0, yesterday)

        mDataBase.ReservationDao().insert(reservation1)
        mDataBase.ReservationDao().insert(reservation2)
        mDataBase.ReservationDao().insert(reservation3)
        val resultat=mDataBase.ReservationDao().getDateEnCours(Calendar.getInstance().time,yesterday)   // la durée d'un jour est la différence du timestamp courant et celui d'un jour de moins
        assertArrayEquals(arrayOf(reservation1,reservation2),resultat.toTypedArray())

    }
}