package edu.rosehulman.uberyard.ui.jobrequest

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import edu.rosehulman.uberyard.Job
import edu.rosehulman.uberyard.R
import kotlinx.android.synthetic.main.fragment_job_request.view.*
import java.sql.Date
import java.text.SimpleDateFormat

class JobRequestFragment : Fragment() {
    val list : Array<String> = arrayOf("Lawn Maintenence", "Tree Removal","Watering System Repairs" , "Other")

    var jobsRef = FirebaseFirestore
        .getInstance()
        .collection("users").document("zimmerjm")
        .collection("jobs")


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
            var username = "zimmerjm"
            var contractorName = "Unknown"
            var jobAddress = root.job_address.text.toString()
            var formatter: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.


                var  dob_var=(root.requested_completion.getText().toString());

                var dateObject : java.util.Date? = formatter.parse(dob_var);

                val date = SimpleDateFormat("dd/MM/yyyy").format(dateObject);
            var jobType = root.job_type.selectedItem.toString()
            var total = 0.00
            when(jobType) {
                ("Lawn Maintenence") -> {
                    total = 35.00
                }
                ("Tree Removal") -> {
                    total = 55.00
                } ("Watering System Repairs") -> {
                total = 100.00
            }
                else -> {
                    total = 50.00
                }
            }

            val job = Job(username, contractorName,jobAddress,  date, jobType, total)
            jobsRef.add(job)
            this.context
        }


        return root
    }


}