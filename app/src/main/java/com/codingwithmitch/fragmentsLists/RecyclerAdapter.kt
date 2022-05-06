package com.codingwithmitch.fragmentsLists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private val onItemClicked: (position: Int, parking: Parking) -> Unit, requireActivity: FragmentActivity): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var parkings = ViewModelProvider(requireActivity).get(MyMod::class.java).list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ctx = parent.context
        val v = LayoutInflater.from(ctx).inflate(R.layout.element1, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemEtat.text = when(parkings[position].etat) {
            Etat.ouvert -> "Ouvert"
            Etat.fermé -> "Fermé"
        }

        holder.itemImage.setImageResource(parkings[position].photo)
        holder.itemTitle.text = parkings[position].nom
        holder.itemLocation.text = parkings[position].lieu
        holder.itemTaux.text = " ${parkings[position].pourcentage}%"
        holder.itemDistance.text = " ${parkings[position].distance}Km"
        holder.itemTemps.text = " ${parkings[position].temps}min"

        holder.itemView.setOnClickListener{

            onItemClicked(position,parkings[position])
        }

    }

    override fun getItemCount(): Int {
        return parkings.size
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
            val park=parkings[position]
//            onItemClicked(this.getItemCount())
//            val intent=Intent(parent.context,CardDetails::class.java)
//                intent.apply { putExtra("id",position)
//                    putExtra("pr","Bill Gates")
//
//                }
//            }

        }

    }




}