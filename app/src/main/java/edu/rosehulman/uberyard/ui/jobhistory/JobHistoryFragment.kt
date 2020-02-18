package edu.rosehulman.uberyard.ui.jobhistory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import edu.rosehulman.uberyard.Job
import edu.rosehulman.uberyard.R

class JobHistoryFragment(val userid : String) : Fragment() {

    var adapter: JobListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView =
            inflater.inflate(R.layout.fragment_job_history, container, false) as RecyclerView
        adapter = JobListAdapter(context!!, userid)
        Log.d("Uber", "${adapter!!.jobs.size} ")

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)



        return recyclerView
    }


}