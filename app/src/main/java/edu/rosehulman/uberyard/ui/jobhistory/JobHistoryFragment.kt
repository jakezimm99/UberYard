package edu.rosehulman.uberyard.ui.jobhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import edu.rosehulman.uberyard.Job
import edu.rosehulman.uberyard.R

class JobHistoryFragment : Fragment() {
    val jobs = ArrayList<Job>()

//    val jobsRef = FirebaseFirestore
//        .getInstance()
//        .collection("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_job_history, container, false)

        return root
    }
}