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
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import java.util.*


class LoginFragment : Fragment() {
    lateinit var navController: NavController
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            // Inflate the layout for this fragment


        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = this.activity?.getSharedPreferences("fileName", Context.MODE_PRIVATE)
        val con = pref?.getBoolean("connected", false)
        navController= Navigation.findNavController(view)
        view?.findViewById<Button>(R.id.log)?.setOnClickListener(View.OnClickListener {
            if( view?.findViewById<EditText>(R.id.pswrd)?.text.toString()=="test" && view?.findViewById<EditText>(R.id.email)?.text.toString()=="test@gmail.com"){
                pref?.edit()?.putBoolean("connected", true)?.apply()
                navController.navigate(R.id.action_loginFragment_to_myReservations2)
            }
            else{
                view?.findViewById<TextView>(R.id.error)?.text="Wrong password or username"
            }
        })
//
//        if (con == false) {
//            Navigation.findNavController(view)!!.navigate(R.id.action_mainFragment_to_viewTransactionFragment )
//        }

    }



}
