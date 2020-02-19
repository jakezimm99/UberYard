package edu.rosehulman.uberyard.ui.jobstatuses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.rosehulman.uberyard.R
import edu.rosehulman.uberyard.ui.jobhistory.JobListAdapter

class JobStatusesFragment(val userid: String) : Fragment() {


    var adapter: JobStatusListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView =
            inflater.inflate(R.layout.fragment_job_statuses, container, false) as RecyclerView
        adapter = JobStatusListAdapter(context!!, userid)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)



        return recyclerView
    }
}