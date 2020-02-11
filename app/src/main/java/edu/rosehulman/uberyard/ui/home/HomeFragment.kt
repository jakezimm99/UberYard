package edu.rosehulman.uberyard.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
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

    var map : GoogleMap? =  null
    override fun onMapReady(map: GoogleMap?) {
        Log.d("Uber", "Here it is")
        this.map = map
        map?.uiSettings?.isMyLocationButtonEnabled = true
        map?.isMyLocationEnabled = true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var fragment = fragmentManager!!.findFragmentById(R.id.map) as MapView
//        fragment.getMapAsync(this)
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

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}