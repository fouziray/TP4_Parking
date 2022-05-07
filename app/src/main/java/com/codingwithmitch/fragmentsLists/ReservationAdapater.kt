package com.codingwithmitch.fragmentsLists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UsersAdapter(private var items: ArrayList<reservationModelForAdapter>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var userDto = items[position]
        holder?.txtName?.text = userDto.name
        holder?.txtComment?.text = userDto.date.toString("yyyy/MM/dd HH:mm:ss")
        holder?.txtPrice?.text = userDto.price.toString()

    }
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.element2, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtName: TextView? = null
        var txtComment: TextView? = null
        var txtPrice: TextView? = null

        init {
            this.txtName = row?.findViewById<TextView>(R.id.reservationName)
            this.txtComment = row?.findViewById<TextView>(R.id.reservationDate)
            this.txtPrice= row?.findViewById<TextView>(R.id.reservationPrix)
        }
    }


}