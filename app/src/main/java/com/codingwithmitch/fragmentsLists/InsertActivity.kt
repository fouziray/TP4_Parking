package com.codingwithmitch.fragmentsLists

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingwithmitch.fragmentsLists.database.MyDatabase
import com.codingwithmitch.fragmentsLists.entities.Reservation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class InsertActivity : AppCompatActivity() {

    lateinit var myDb: MyDatabase
    lateinit var note: Reservation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = intent
        val price = intent.getStringExtra("price")
        val name = intent.getStringExtra("name")
        myDb = MyDatabase.getInstance(this)!!  // call database
        note = Reservation()
        if (name != null) {
            note.desc = name
        }
        note.idReservation = 0

        setupData()
        AfficherAll()
    }

    private fun setupData(){
        val noteId = intent.getStringExtra("extra").toString()
        val myDb: MyDatabase? = MyDatabase.getInstance(this) // call database
        val note = Reservation() //create new transaction
        val intent = intent
        val price = intent.getIntExtra("price",0)
        val name = intent.getStringExtra("name")

        if (name != null) {
            note.desc = name
        }
        note.idReservation = 0
        if (price != null) {
            note.price= price.toDouble()
        }
        //calculate yesterday's date as Date object
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -5)
        val yesterday = calendar.time
        println("yesterday: " + yesterday) // just for inserting other datas

        note.date= Calendar.getInstance().time
        CompositeDisposable().add(Observable.fromCallable {
            myDb?.ReservationDao()?.insert(note)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                Log.d("response", "data inserted")
            })
    }

    private fun AfficherAll(){
        val myDb: MyDatabase? = MyDatabase.getInstance(this) // call database
        val ca=Calendar.getInstance()
           ca.add(Calendar.DATE,-1)
        val yesterday=ca.time
        val listNote = myDb?.ReservationDao()?.getAll() // get All data
        if (listNote != null) {
            for(note :Reservation in listNote){
                println("-----------------------")
                println(note.desc)
                println(note.idReservation)
                println(note.price)
                println(note.date.toString("yyyy/MM/dd HH:mm:ss"))
            }
        }
//        btn_delete.setOnClickListener {
//            delete()
//        }
//
//        btn_back.setOnClickListener {
//            update()
//        }
    }


    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
    private fun delete() {
        // insert data
        CompositeDisposable().add(
            Observable.fromCallable {
                myDb.ReservationDao().delete(note) // Delete note
            }.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("respons", "data deleted")
                    Toast.makeText(this, "Data deleted", Toast.LENGTH_SHORT).show()
                    finish()
                })
    }

}