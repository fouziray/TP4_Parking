package com.codingwithmitch.fragmentsLists


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.fragmentsLists.database.MyDatabase
import com.codingwithmitch.fragmentsLists.entities.Reservation
import io.reactivex.Completable.fromCallable
import io.reactivex.Flowable.fromCallable
import io.reactivex.Maybe.fromCallable
import io.reactivex.Observable.fromCallable
import io.reactivex.Single.fromCallable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*


class ViewTransactionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_view_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val posi= arguments?.getInt("position")?.toInt()

        val vm=ViewModelProvider(requireActivity()).get(MyMod::class.java)
        println("hii ss  "+vm.list[posi!!].nom)

        val essai = view?.findViewById<TextView>(R.id.jour)
        val park= vm.list[posi!!]
        essai?.text = "19/05/2022"
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK]
        var jour = ""
        when (day) {
            Calendar.SUNDAY -> {
                jour="Dimanche"
            }
            Calendar.MONDAY -> {
                jour="Lundi"
            }
            Calendar.TUESDAY -> {
                jour="Mardi"
            }
            Calendar.WEDNESDAY -> {
                jour="Mercredi"
            }
            Calendar.THURSDAY -> {
                jour="Jeudi"
            }
            Calendar.FRIDAY -> {
                jour="Vendredi"
            }
            Calendar.SATURDAY -> {
                jour="Samedi"
            }
        }
        view?.findViewById<ImageView>(R.id.mapsbutton)?.setOnClickListener(View.OnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:0,0?q="+park.nom+" "+park.lieu)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        })
        view.findViewById<TextView>(R.id.nomParking)?.text = park.nom
        view.findViewById<TextView>(R.id.nomParking2)?.text = park.lieu
        view.findViewById<TextView>(R.id.estimated)?.text = " à environs "+park.distance.toString()+" km - "+park.temps.toString()+" min "
        view?.findViewById<TextView>(R.id.fermeouvert)?.text = park.etat.toString()
        view?.findViewById<TextView>(R.id.tarif)?.text = park.tarif.toString()
        view?.findViewById<TextView>(R.id.horaireOuverture)?.text = "De "+park.ouverture.toString()+"h"
        view?.findViewById<TextView>(R.id.horaireFermeture)?.text = park.fermeture.toString()+"h"
        view?.findViewById<TextView>(R.id.pourcentage)?.text = park.pourcentage.toString()+"%"
        view?.findViewById<TextView>(R.id.jour)?.text = jour

        view?.findViewById<Button>(R.id.inserttransaction)?.setOnClickListener(View.OnClickListener {
//          change activity to insertActivity
            val intent = Intent(activity, InsertActivity::class.java)
            println("hii")
           startActivity(intent)
            println("hii")
        })
    }



}
