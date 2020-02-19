package edu.rosehulman.uberyard.ui.jobrequest

import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import edu.rosehulman.uberyard.Job
import edu.rosehulman.uberyard.R
import kotlinx.android.synthetic.main.fragment_billing.view.*
import kotlinx.android.synthetic.main.fragment_job_request.view.*
import java.sql.Date
import java.text.SimpleDateFormat
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat.getSystemService



class JobRequestFragment(val userid: String) : Fragment() {
    val list: Array<String> =
        arrayOf("Lawn Maintenence", "Tree Removal", "Watering System Repairs", "Other")

    var jobsRef = FirebaseFirestore
        .getInstance()
        .collection("users").document(userid)
        .collection("jobs")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_job_request, container, false)
        val spinner = root.findViewById<Spinner>(R.id.job_type)
        var adapter: ArrayAdapter<String> =
            ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected = parent!!.getItemAtPosition(position).toString()
                Log.d("Uber", selected)
                if (selected.equals(list[0])) {
                    root.findViewById<TextView>(R.id.subtotal_amount).text = "$35.00"
                    root.findViewById<TextView>(R.id.total_amount).text = "$40.00"
                } else if (selected.equals(list[1])) {
                    root.findViewById<TextView>(R.id.subtotal_amount).text = "$55.00"
                    root.findViewById<TextView>(R.id.total_amount).text = "$60.00"
                } else if (selected.equals(list[2])) {
                    root.findViewById<TextView>(R.id.subtotal_amount).text = "$100.00"
                    root.findViewById<TextView>(R.id.total_amount).text = "$105.00"
                } else {
                    root.findViewById<TextView>(R.id.subtotal_amount).text = "$50.00"
                    root.findViewById<TextView>(R.id.total_amount).text = "$55.00"
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        val button = root.findViewById<Button>(R.id.request_job)
        button.setOnClickListener {
            var username = userid
            var contractorName = "Unknown"
            var jobAddress = root.job_address.text.toString()
            var formatter: SimpleDateFormat =
                SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.


            var dob_var = (root.requested_completion.getText().toString());

            var dateObject: java.util.Date? = formatter.parse(dob_var);

            val date = SimpleDateFormat("dd/MM/yyyy").format(dateObject);
            var jobType = root.job_type.selectedItem.toString()
            var total = 0.00
            when (jobType) {
                ("Lawn Maintenence") -> {
                    total = 35.00
                }
                ("Tree Removal") -> {
                    total = 55.00
                }
                ("Watering System Repairs") -> {
                    total = 100.00
                }
                else -> {
                    total = 50.00
                }
            }
            Log.d("Uber", "${jobsRef.path}")
            val job = Job(username, contractorName, jobAddress, date, jobType, total)
            jobsRef.add(job).addOnSuccessListener {
                Log.d("Uber", "Added the job to $userid")
                root.findViewById<EditText>(R.id.job_address).setText("")
                root.findViewById<EditText>(R.id.requested_completion).setText("")
                Snackbar.make(root, "Job Successfully Added!", Snackbar.LENGTH_SHORT)
                    .setAction("CLOSE", View.OnClickListener {

                    })
                    .setActionTextColor(Color.GREEN)
                    .show()
                root.findViewById<EditText>(R.id.requested_completion).onEditorAction(EditorInfo.IME_ACTION_DONE)
            }



            this.context
        }


        return root
    }


}