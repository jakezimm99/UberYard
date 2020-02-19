package edu.rosehulman.uberyard

import android.annotation.SuppressLint
import android.os.Parcelable
import android.widget.DatePicker
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize
import java.sql.Time
import java.time.Instant
import java.time.LocalDate
import java.util.*


@Parcelize
data class Job(
    val username: String = "",
    val contractor: String = "",
    val jobAdress: String = "",
    val requestedCompletion: String = Calendar.getInstance().time.toString(),
    val jobType: String = "",
    val total: Double = 0.00,
    var status: String = "incomplete"
) : Parcelable {

    @get:Exclude
    var id: String = ""

    companion object {

        fun from(snapshot: DocumentSnapshot): Job {
            val pic = snapshot.toObject(Job::class.java)!!
            pic.id = snapshot.id
            return pic
        }
    }

}