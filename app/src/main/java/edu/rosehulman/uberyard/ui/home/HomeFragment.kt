package edu.rosehulman.uberyard.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import edu.rosehulman.uberyard.R

//import com.google.android.gms.maps.R

class HomeFragment : Fragment(), OnMapReadyCallback {

    var map : GoogleMap? =  null

    var mapView: MapView? = null
    override fun onMapReady(map: GoogleMap?) {
        Log.d("Uber", "Here it is! Map finished")
        this.map = map

        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            map?.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 17)
        }
        map?.uiSettings?.isMyLocationButtonEnabled = true


    }





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        MapsInitializer.initialize(this.activity)
//        if(mapView != null) {
//            mapView!!.onCreate(savedInstanceState)
//
//        }
        val map = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        map.getMapAsync(this)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mapView!!.onCreate(savedInstanceState)
//        var fragment = fragmentManager!!.findFragmentById(R.id.map) as MapView
//        fragment.getMapAsync(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)


        Log.d("Uber", "Map is being created.")
        return root
    }


}