package edu.rosehulman.uberyard.ui.jobhistory

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

class JobListAdapter(var context: Context, val userid : String) : RecyclerView.Adapter<JobViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.job_item, parent, false)
        return JobViewHolder(view, this)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobs[position]
        holder.bind(job)
    }


    fun delete(position: Int) {
        // TODO
    }
}
