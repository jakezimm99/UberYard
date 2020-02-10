package edu.rosehulman.uberyard.ui.jobhistory

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.rosehulman.uberyard.Job
import kotlinx.android.synthetic.main.job_item.view.*

class JobViewHolder (itemView: View, adapter: JobListAdapter) : RecyclerView.ViewHolder(itemView) {
        val address = itemView.show_job_address
    val completionDate = itemView.show_date
    val total = itemView.show_total
    val contractor = itemView.contractor
    val jobType = itemView.show_job_type

    init {
        itemView.setOnClickListener {
        }

        itemView.setOnLongClickListener {
            true
        }
    }

    fun bind(job: Job) {
        address.text = job.jobAdress
        contractor.text = "Contractor: " + job.contractor
        completionDate.text = "Complete By: " + job.requestedCompletion
        total.text = "$" + job.total.toString() +"0"
        jobType.text = job.jobType

    }
}