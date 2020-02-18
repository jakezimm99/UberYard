package edu.rosehulman.uberyard.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.type.LatLng
import edu.rosehulman.uberyard.R
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import android.graphics.Canvas


//import com.google.android.gms.maps.R

class HomeFragment(val userid: String) : Fragment(), OnMapReadyCallback {

    var locations = FirebaseFirestore
        .getInstance()
        .collection("users").get()

    var myLocationRef = FirebaseFirestore
        .getInstance()
        .collection("users").document(userid)

    var map: GoogleMap? = null
    var list: ArrayList<com.google.android.gms.maps.model.LatLng> = arrayListOf()

    var mapView: MapView? = null
    override fun onMapReady(map: GoogleMap?) {
        Log.d("Uber", "Here it is! Map finished")
        this.map = map
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            map?.isMyLocationEnabled = true

        } else {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                17
            )
        }
        map?.uiSettings?.isMyLocationButtonEnabled = true
        map!!.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                com.google.android.gms.maps.model.LatLng(
                    39.483693,
                    -87.317863
                ), 12F
            )
        )
        for (item in list) {
            map!!.addMarker(
                MarkerOptions()
                    .position(item)
                    .anchor(0.5F, 0.5F)
                    .title("Nearby User")
                    .snippet("User")
                    .icon(
                        bitmapDescriptorFromVector(
                            context!!,
                            R.drawable.ic_person_pin_circle_black_24dp
                        )
                    )
            )
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, @DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor {
        val background =
            ContextCompat.getDrawable(context, R.drawable.ic_person_pin_circle_black_24dp)
        background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(
            40,
            20,
            vectorDrawable.intrinsicWidth + 40,
            vectorDrawable.intrinsicHeight + 20
        )
        val bitmap = Bitmap.createBitmap(
            background.intrinsicWidth,
            background.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        background.draw(canvas)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
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

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        locations.addOnSuccessListener {
            it.forEach { item: QueryDocumentSnapshot? ->
                var temp = item?.getGeoPoint("location")
                var lattemp = temp?.latitude
                var longtemp = temp?.longitude
                list.add(com.google.android.gms.maps.model.LatLng(lattemp!!, longtemp!!))

            }

        }
        Log.d("Uber", "Map is being created.")
        return root
    }


}