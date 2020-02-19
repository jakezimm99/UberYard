package edu.rosehulman.uberyard.ui.jobstatuses

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.google.common.io.Resources
import edu.rosehulman.uberyard.Job
import edu.rosehulman.uberyard.R
import kotlinx.android.synthetic.main.job_item.view.*

class JobStatusViewHolder (itemView: View, adapter: JobStatusListAdapter) : RecyclerView.ViewHolder(itemView) {
    val address = itemView.show_job_address
    val completionDate = itemView.show_date
    val total = itemView.show_total
    val contractor = itemView.contractor
    val jobType = itemView.show_job_type

    init {
        itemView.setOnClickListener {
        }

        itemView.setOnLongClickListener {
            adapter.changeStatus(adapterPosition)
            true
        }
    }

    fun bind(job: Job) {
        if(job.status.equals("incomplete")) {
            itemView.background = Color.parseColor("#FFBF00").toDrawable()
        } else {
            itemView.background = Color.parseColor("#999999").toDrawable()
        }
        address.text = job.jobAdress
        contractor.text = "Contractor: " + job.contractor
        completionDate.text = "Complete By: " + job.requestedCompletion
        total.text = "$" + job.total.toString() +"0"
        jobType.text = job.jobType

    }
}