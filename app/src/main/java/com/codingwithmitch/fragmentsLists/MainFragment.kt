package com.codingwithmitch.fragmentsLists


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var layoutManager = LinearLayoutManager(activity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager

        var viewModel = ViewModelProvider(requireActivity()).get(MyMod::class.java)
        viewModel.list.add(
            Parking(R.drawable.parking, "Parking esi", "oued smar", Etat.ouvert, 30, 50, 40,8,16,20))
        viewModel.list.add(Parking(R.drawable.parking, "Parking resto", "ESI", Etat.fermé, 20, 50, 40,8,16,20))
        viewModel.list.add(Parking(R.drawable.parking, "Parking visio", "Alger", Etat.fermé, 90, 50, 40,8,16,20))
        viewModel.list.add(Parking(R.drawable.parking, "Parking esi", "Alger", Etat.ouvert, 80, 50, 40,8,16,20))
        viewModel.list.add(Parking(R.drawable.parking, "Parking oued smar", "Alger", Etat.ouvert, 80, 50, 40,8,16,20))
        viewModel.list.add(Parking(R.drawable.parking, "Parking oued smar", "Alger", Etat.ouvert, 80, 50, 40,8,16,20))

        var adapter = RecyclerAdapter ({ position, parking -> onListItemClick(position, parking) },requireActivity())
        recyclerView.adapter = adapter

        navController = Navigation.findNavController(view)
        view?.findViewById<Button>(R.id.myreserva)?.setOnClickListener(View.OnClickListener {
            navController!!.navigate(R.id.action_mainFragment_to_myReservations2)
        })

        val pref = this.activity?.getSharedPreferences("fileName", Context.MODE_PRIVATE)

        if (pref != null) {
            pref.edit {
                putBoolean("connected", false)
            }
        }
    }



    override fun onClick(v: View?) {
        when(v!!.id){
//            R.id.view_transactions_btn -> navController!!.navigate(R.id.action_mainFragment_to_viewTransactionFragment)
//            R.id.send_money_btn -> navController!!.navigate(R.id.action_mainFragment_to_chooseRecipientFragment)
//            R.id.view_balance_btn -> navController!!.navigate(R.id.action_mainFragment_to_viewBalanceFragment)
//            R.id.card_view -> navController!!.navigate(R.id.action_mainFragment_to_viewBalanceFragment)
        }
    }


    private fun onListItemClick(position: Int,parking: Parking) {
//        val intent=Intent(this, CardDetails::class.java)
//        intent.apply { putExtra("id",position)
//            putExtra("park",parking)
//
//        }
////        startActivity(intent)
//        setContentView(R.layout.fragment_meme)=

        var bundle = bundleOf("position" to position)
        MyModel.getInstance().pos=position
        navController!!.navigate(R.id.action_mainFragment_to_viewTransactionFragment,bundle )

        println("other message"+position+MyModel.getInstance().list)

    }

}
