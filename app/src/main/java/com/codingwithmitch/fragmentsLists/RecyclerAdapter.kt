package com.codingwithmitch.fragmentsLists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.codingwithmitch.fragmentsLists.ViewModels.ParkingViewModel
import com.codingwithmitch.fragmentsLists.entities.paginatedParkings
import com.codingwithmitch.fragmentsLists.entities.parkingjdid

class RecyclerAdapter (private val onItemClicked: (position: Int, parking: Parking) -> Unit, requireActivity: FragmentActivity): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    //private var parkings = ViewModelProvider(requireActivity).get(MyMod::class.java).list
    private var parkings = ViewModelProvider(requireActivity).get(ParkingViewModel::class.java).parkings
    // get first element of parkings

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ctx = parent.context
        val v = LayoutInflater.from(ctx).inflate(R.layout.element1, parent, false)
        return ViewHolder(v)
    }

//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemEtat.text = when(parkings[position].etat) {
//            Etat.ouvert -> "Ouvert"
//            Etat.fermé -> "Fermé"
//        }
//
//        holder.itemImage.setImageResource(parkings[position].photo)
//        holder.itemTitle.text = parkings[position].nom
//        holder.itemLocation.text = parkings[position].lieu
//        holder.itemTaux.text = " ${parkings[position].pourcentage}%"
//        holder.itemDistance.text = " ${parkings[position].distance}Km"
//        holder.itemTemps.text = " ${parkings[position].temps}min"
//        holder.itemView.setOnClickListener{
//
//            onItemClicked(position,parkings[position])
//        }
//
//    }
@SuppressLint("SetTextI18n")
override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.itemEtat.text = parkings.value?.data?.get(position)?.etatParking
//        when(parkings.value?.get(position)?.etatParking) {
//        Etat.OUVERT-> "Ouvert"
//        Etat.FERME -> "Fermé"yyyyyyyy
//        else -> {
//            "Erreur"
//        }
//    }



    holder.itemImage.setImageResource(R.drawable.parkisley)
    holder.itemTitle.text = parkings.value?.data?.get(position)?.nomParking
    holder.itemLocation.text = parkings.value?.data?.get(position)?.adresseParking
    holder.itemTaux.text = " ${parkings.value?.data?.get(position)?.nbPlacesDisponiblesParking}%"
    holder.itemDistance.text = "20 Km"
    holder.itemTemps.text = " 20 min"
    holder.itemView.setOnClickListener{

    }

}

    override fun getItemCount(): Int {
       // return parkings.size
        return parkings.value?.data?.size?:0
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var itemEtat: TextView
        var itemImage: ImageView
        var itemTitle: TextView
        var itemLocation: TextView
        var itemTaux: TextView
        var itemDistance: TextView
        var itemTemps: TextView

        init {
            itemEtat = itemView.findViewById(R.id.item_etat)
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemLocation = itemView.findViewById(R.id.item_location)
            itemTaux = itemView.findViewById(R.id.item_taux)
            itemDistance = itemView.findViewById(R.id.item_distance)
            itemTemps = itemView.findViewById(R.id.item_temps)
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View) {
            val position = 0
            //val park=parkings[position]
//            onItemClicked(this.getItemCount())
//            val intent=Intent(parent.context,CardDetails::class.java)
//                intent.apply { putExtra("id",position)
//                    putExtra("pr","Bill Gates")
//
//                }
//            }

        }


    }


    fun setParkings(parkings: paginatedParkings) {
        //wrap parkings inside mutablelivedata
        this.parkings = MutableLiveData(parkings)
        notifyDataSetChanged()
    }

}