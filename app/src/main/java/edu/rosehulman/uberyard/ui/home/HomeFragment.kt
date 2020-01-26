package edu.rosehulman.uberyard.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import edu.rosehulman.uberyard.R

class HomeFragment : Fragment(), OnMapReadyCallback {
    override fun onMapReady(map: GoogleMap?) {
        val map = map
        map?.uiSettings?.isMyLocationButtonEnabled = true
        map?.isMyLocationEnabled = true
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val mapView = root.findViewById<MapView>(R.id.map)
//        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync(this)
        Log.d("Uber", "Map is being created.")

        return root
    }
}