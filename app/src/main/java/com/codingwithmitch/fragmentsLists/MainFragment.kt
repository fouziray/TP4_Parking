package com.codingwithmitch.fragmentsLists


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithmitch.fragmentsLists.ViewModels.ParkingViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tomtom.sdk.common.location.GeoCoordinate
import com.tomtom.sdk.maps.display.TomTomMap
import com.tomtom.sdk.maps.display.camera.CameraOptions
import com.tomtom.sdk.maps.display.image.ImageFactory
import com.tomtom.sdk.maps.display.marker.BalloonViewAdapter
import com.tomtom.sdk.maps.display.marker.Marker
import com.tomtom.sdk.maps.display.marker.MarkerOptions
import com.tomtom.sdk.maps.display.ui.MapFragment
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    private lateinit var fusedLocationClient: FusedLocationProviderClient

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

        //var viewModel = ViewModelProvider(requireActivity()).get(MyMod::class.java)
        var parkingViewModel = ViewModelProvider(requireActivity()).get(ParkingViewModel::class.java)
        parkingViewModel.errorMessage.value=null
        //var userviewmodel= ViewModelProvider(requireActivity()).get(UtilisateurViewModel::class.java)
      //  userviewmodel.getuser()
        //on error set value to null
//        viewModel.list.add(
//            Parking(R.drawable.parking, "Parking esi", "oued smar", Etat.ouvert, 30, 50, 40,8,16,20))
//        viewModel.list.add(Parking(R.drawable.parking, "Parking resto", "ESI", Etat.fermé, 20, 50, 40,8,16,20))
//        viewModel.list.add(Parking(R.drawable.parking, "Parking visio", "Alger", Etat.fermé, 90, 50, 40,8,16,20))
//        viewModel.list.add(Parking(R.drawable.parking, "Parking esi", "Alger", Etat.ouvert, 80, 50, 40,8,16,20))
//        viewModel.list.add(Parking(R.drawable.parking, "Parking oued smar", "Alger", Etat.ouvert, 80, 50, 40,8,16,20))
//        viewModel.list.add(Parking(R.drawable.parking, "Parking oued smar", "Alger", Etat.ouvert, 80, 50, 40,8,16,20))

        var adapter = RecyclerAdapter ({ position, parking -> onListItemClick(position, parking) },requireActivity())
        recyclerView.adapter = adapter

        parkingViewModel.getParkings()
        // add Observers
        // loading observer
        parkingViewModel.loading.observe(requireActivity(), Observer { loading ->
            if (loading) {
                view?.progressBarHome.visibility = View.VISIBLE
            } else {
                view?.progressBarHome.visibility = View.GONE
            }

        })
        // Error message observer
        parkingViewModel.errorMessage.observe(requireActivity(), Observer { message ->
            if(message!=null){
                Toast.makeText(requireContext(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }


        })
        // List movies observer
        parkingViewModel.parkings.observe(requireActivity(), Observer { parkings ->
            adapter.setParkings(parkings)
        })


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

        val mapFragment = childFragmentManager.findFragmentById(R.id.googleMaps) as? MapFragment
        mapFragment?.getMapAsync { tomtomMap: TomTomMap ->
            val cameraPosition = tomtomMap.cameraPosition()
        /* Your code goes here */
            val amsterdam = GeoCoordinate(52.379189, 4.899431)
            val cameraOptions = CameraOptions(

                position = amsterdam,

                zoom = 10.0,

                tilt = 45.0,

                rotation = 90.0

            )
            tomtomMap.moveCamera(cameraOptions)
            val markerBuilder = MarkerOptions(

                coordinate = amsterdam,

                pinImage = ImageFactory.fromResource(R.drawable.marker)
            )
            val markerBuilder2 = MarkerOptions(

                coordinate = GeoCoordinate(51.9244, 4.4777),

                pinImage = ImageFactory.fromResource(R.drawable.marker)
            )
            mapFragment.markerBalloonViewAdapter = context?.let { CustomBalloonViewAdapter(it) }!!


            tomtomMap.addMarker(markerBuilder)
            tomtomMap.addMarker(markerBuilder2)
            tomtomMap.addOnMarkerClickListener { marker: Marker ->
                if (!marker.isSelected()) {
                   for (othermarker in tomtomMap.markers ) {
                       if (othermarker.isSelected()){
                            othermarker.deselect()
                           othermarker.setPinIconImage(ImageFactory.fromResource(R.drawable.marker))

                       }
                   }
                    fusedLocationClient= activity?.let {
                        LocationServices.getFusedLocationProviderClient(
                            it
                        )
                    }!!
                    marker.select()
                    marker.setPinIconImage(ImageFactory.fromResource(R.drawable.greenmarkekr))
                }else{
                    marker.deselect()
                    marker.setPinIconImage(ImageFactory.fromResource(R.drawable.marker))
                }
            /* Your code goes here */ }
        }
        val barre=view.findViewById<LinearLayout>(R.id.linearBar)
        val vueliste= view.findViewById<ConstraintLayout>(R.id.vueliste)

        val mapsButton= view.findViewById<Button>(R.id.mapsButton)
        val listeButton= view.findViewById<Button>(R.id.listeButton2)

        listeButton.setOnClickListener {
            vueliste.animate().translationY(0f)
                .alpha(1.0f)

        }
        mapsButton.setOnClickListener  {

            vueliste.animate()
                .translationY(vueliste.height.toFloat())
                .alpha(1.0f)
                .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                               // vueliste.visibility = View.GONE
                            }
                        })
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
class CustomBalloonViewAdapter(private val context: Context) : BalloonViewAdapter {


    override fun onCreateBalloonView(marker: Marker): View {

        val view = LayoutInflater.from(context).inflate(R.layout.mapsmarkerdetail, null)

        val balloonTextTv = view.findViewById<TextView>(R.id.tauxdetailmap)

        balloonTextTv.text = marker.balloonText

        return view

    }

}