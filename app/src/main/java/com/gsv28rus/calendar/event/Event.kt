package com.gsv28rus.calendar.event

import android.os.Parcel
import android.os.Parcelable
import com.gsv28rus.calendar.common.parseToHoursAndMinutes
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.TextStyle
import java.util.*

data class Event(
    val id: String?,
    var userId: String?,
    var title: String?,
    var description: String?,
    var location: String?,
    var startDate: ZonedDateTime?,
    var endDate: ZonedDateTime?,
    var who: String?,
    var repeat: Repeat?
) : Parcelable {

    fun getPeriod(): String? {
        return "${startDate?.parseToHoursAndMinutes()} - ${endDate?.parseToHoursAndMinutes()}"
    }

    fun getHoursAndMinutes(zonedDateTime: ZonedDateTime?) : String? {
        return zonedDateTime?.parseToHoursAndMinutes()
    }

    fun getDayAndWeek(zonedDateTime: ZonedDateTime?) : String {
        val month = zonedDateTime?.month?.getDisplayName(TextStyle.SHORT, Locale.getDefault())?.toLowerCase()
        val dayOfMonth = zonedDateTime?.dayOfMonth
        val dayOfWeek = zonedDateTime?.dayOfWeek?.getDisplayName(TextStyle.SHORT, Locale.getDefault())?.toLowerCase()
        return "$dayOfWeek, $dayOfMonth $month."
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        ZonedDateTime.parse(parcel.readString()),
        ZonedDateTime.parse(parcel.readString()),
        parcel.readString(),
        Repeat.valueOf(parcel.readString()!!)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(userId)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(location)
        parcel.writeString(startDate.toString())
        parcel.writeString(endDate.toString())
        parcel.writeString(who)
        parcel.writeString(repeat.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}