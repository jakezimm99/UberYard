package edu.rosehulman.uberyard.ui.jobstatuses

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import edu.rosehulman.uberyard.Job
import edu.rosehulman.uberyard.R
import edu.rosehulman.uberyard.ui.jobhistory.JobViewHolder

class JobStatusListAdapter(var context: Context, val userid : String) : RecyclerView.Adapter<JobStatusViewHolder>() {
    var jobs = ArrayList<Job>()


    var jobsRef = FirebaseFirestore
        .getInstance()
        .collection("users").document(userid)
        .collection("jobs")

    init {
        addListener()
    }

    fun addListener() {
        jobsRef
            .addSnapshotListener { snapshot: QuerySnapshot?, exception: FirebaseFirestoreException? ->
                if (exception != null) {

                    Log.e("Uber", "Error with uber listener $exception")
                    return@addSnapshotListener
                }
                for (docChange in snapshot!!.documentChanges) {
                    val doc = Job.from(docChange.document)
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            jobs.add(0, doc)
                            notifyItemInserted(0)
                        }
                        DocumentChange.Type.REMOVED -> {
                            for ((i, pic) in jobs.withIndex()) {
                                if (pic.id == doc.id) {

                                    delete(i)
                                    break
                                }
                            }
                        }
                        DocumentChange.Type.MODIFIED -> {
                            val k = jobs.indexOfFirst { it.id == doc.id }
                            jobs[k] = doc
                            notifyItemChanged(k)
                        }
                    }
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobStatusViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.job_item, parent, false)
        return JobStatusViewHolder(view, this)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(holder: JobStatusViewHolder, position: Int) {
        val job = jobs[position]
        holder.bind(job)
    }


    fun delete(position: Int) {
        // TODO
    }

    fun changeStatus(position: Int) {
        jobs.get(position).status = "complete"
        notifyItemChanged(position)
        jobsRef.document(jobs.get(position).id).set(jobs[position])

    }
}