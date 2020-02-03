package edu.rosehulman.uberyard.ui.jobrequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import edu.rosehulman.uberyard.R

class JobRequestFragment : Fragment() {
    val list : Array<String> = arrayOf("Lawn Maintenence", "Tree Removal","Watering System Repairs" , "Other")

    var jobs = FirebaseFirestore.getInstance().collection("user")


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
        val button = root.findViewById<Button>(R.id.request_job)
        button.setOnClickListener {
            var job = constructJob(root)
        }


        return root
    }

    fun constructJob(view: View) {
        val username = ""

    }
}