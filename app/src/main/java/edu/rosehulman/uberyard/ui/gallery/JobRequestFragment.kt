package edu.rosehulman.uberyard.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edu.rosehulman.uberyard.R

class GalleryFragment : Fragment() {
    val list : Array<String> = arrayOf("Lawn Maintenence", "Tree Removal","Watering System Repairs" , "Other")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_job_request, container, false)
        val spinner = root.findViewById<Spinner>(R.id.job_type)
        var adapter : ArrayAdapter<String> = ArrayAdapter(activity!!.applicationContext ,  android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        return root
    }
}