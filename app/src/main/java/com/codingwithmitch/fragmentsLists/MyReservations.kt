package com.codingwithmitch.fragmentsLists


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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

    }



}
