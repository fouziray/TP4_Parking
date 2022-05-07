package com.codingwithmitch.fragmentsLists


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithmitch.fragmentsLists.database.MyDatabase
import com.codingwithmitch.fragmentsLists.entities.Reservation
import java.util.*


class MyReservations : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_mes_reservatison, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = this.activity?.getSharedPreferences("fileName", Context.MODE_PRIVATE)
        val con = pref?.getBoolean("connected", false)
        var navController = Navigation.findNavController(view)
        view?.findViewById<Button>(R.id.deconnecter)?.setOnClickListener(View.OnClickListener {
            navController!!.navigate(R.id.action_myReservations2_to_mainFragment)
            pref?.edit()?.putBoolean("connected", false)?.apply()
        })
        if (con == false) {
            Navigation.findNavController(view)!!.navigate(R.id.action_myReservations2_to_loginFragment )
        }
        var recyclerView: RecyclerView? = null
        recyclerView = view?.findViewById(R.id.recyclerReservations)

        var adapter = UsersAdapter(getAllReservations())
        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()

        recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
        val toggle: Switch = view?.findViewById(R.id.switch1)
                toggle.setOnClickListener(View.OnClickListener {
                    adapter = if(toggle.isChecked){
                        UsersAdapter(getTodayReservations())

                    }else{
                        UsersAdapter(getAllReservations())
                    }
                    adapter.notifyDataSetChanged()
                    recyclerView?.adapter = adapter

                })





    }
    fun getTodayReservations(): ArrayList<reservationModelForAdapter> {
        val myDb: MyDatabase? = context?.let { MyDatabase.getInstance(it) } // call database
        val ca=Calendar.getInstance()
        ca.add(Calendar.DATE,-1)
        val yesterday=ca.time
        var result = ArrayList<reservationModelForAdapter>()

        val listNote = myDb?.ReservationDao()?.getDateEnCours(Calendar.getInstance().time,yesterday) // get All data
        if (listNote != null) {
            for(note :Reservation in listNote){
                var user: reservationModelForAdapter = reservationModelForAdapter(note.desc,note.date,note.price)
                result.add(user)


            }
        }
        return result
    }
    fun getAllReservations(): ArrayList<reservationModelForAdapter> {
        val myDb: MyDatabase? = context?.let { MyDatabase.getInstance(it) } // call database
        val ca=Calendar.getInstance()
        ca.add(Calendar.DATE,-1)
        val yesterday=ca.time
        var result = ArrayList<reservationModelForAdapter>()

        val listNote = myDb?.ReservationDao()?.getAll() // get All data
        if (listNote != null) {
            for(note :Reservation in listNote){
                var user: reservationModelForAdapter = reservationModelForAdapter(note.desc,note.date,note.price)
                result.add(user)


            }
        }
        return result
    }
    fun generateData(): ArrayList<reservationModelForAdapter> {
        var result = ArrayList<reservationModelForAdapter>()
//today date and time

        for (i in 0..9) {
            var user: reservationModelForAdapter = reservationModelForAdapter("algei",Calendar.getInstance().time,0.2 )
            result.add(user)
        }

        return result
    }




}
