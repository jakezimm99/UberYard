package edu.rosehulman.uberyard

import android.annotation.SuppressLint
import android.os.Parcelable
import android.widget.DatePicker
import kotlinx.android.parcel.Parcelize
import java.sql.Time
import java.time.Instant
import java.time.LocalDate
import java.util.*


@Parcelize
data class Job(
    val username: String = "",
    val contractor: String = "",
    val requestedCompletion: Date = Calendar.getInstance().time,
    val jobType: String = "",
    val total: Int = 0
) : Parcelable {

}