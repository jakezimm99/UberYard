package edu.rosehulman.uberyard.ui.jobhistory

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.rosehulman.uberyard.Job
import kotlinx.android.synthetic.main.job_item.view.*

class JobViewHolder (itemView: View, adapter: JobListAdapter) : RecyclerView.ViewHolder(itemView) {
        val address = itemView.show_job_address
    val username = itemView.username
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
        username.text = job.username
        contractor.text = job.contractor
        completionDate.text = job.requestedCompletion
        total.text = job.total.toString()
        jobType.text = job.jobType

    }
}