package com.codingwithmitch.fragmentsLists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codingwithmitch.fragmentsLists.database.MyDatabase
import com.codingwithmitch.fragmentsLists.entities.Reservation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class InsertActivity : AppCompatActivity() {

    lateinit var myDb: MyDatabase
    lateinit var note: Reservation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_view_transaction)
        myDb = MyDatabase.getInstance(this)!!  // call database
        note = Reservation()
        note.desc = "First reservation"
        note.idReservation = 0
        setupData()
        mainButton()
    }

    private fun setupData(){
//        val noteId = intent.getStringExtra("extra").toString()
        val myDb: MyDatabase? = MyDatabase.getInstance(this) // call database
        val note = Reservation() //create new transaction
        note.desc = "First reservation"
        note.idReservation = 0
        CompositeDisposable().add(Observable.fromCallable {
            myDb?.ReservationDao()?.insert(note)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                Log.d("response", "data inserted")
            })
    }

    private fun mainButton(){
        val myDb: MyDatabase? = MyDatabase.getInstance(this) // call database
        val listNote = myDb?.ReservationDao()?.getAll() // get All data
        if (listNote != null) {
            for(note :Reservation in listNote){
                println("-----------------------")
                println(note.desc)
                println(note.idReservation)
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

    private fun update() {
//        if (edt_title.text.isEmpty()) {
//            edt_title.error = "field is required"
//            edt_title.requestFocus()
//            return
//        }
//
//        note.title = edt_title.text.toString()
//        note.description = edt_title.text.toString()
//
//        // insert data
//        CompositeDisposable().add(
//            Observable.fromCallable {
//                myDb.daoNote().update(note) // Update note
//            }.subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    Log.d("respons", "data update")
//                    finish()
//                })
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

    override fun onBackPressed() {
        update()
    }
}