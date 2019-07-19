package com.gsv28rus.calendar.event

import android.os.Parcel
import android.os.Parcelable
import org.threeten.bp.DayOfWeek
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.TextStyle
import org.threeten.bp.temporal.WeekFields
import java.util.*

data class EventDay(
    var startDay: ZonedDateTime,
    var endDay: ZonedDateTime,
    var event: Event?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        ZonedDateTime.parse(parcel.readString()),
        ZonedDateTime.parse(parcel.readString()),
        parcel.readParcelable(Event::class.java.classLoader)
    )

    fun getDayOfMouth(): String? {
        return startDay.dayOfMonth.toString()
    }

    fun getDayOfWeek(): String? {
        return startDay.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    }

    fun isBeginningWeek(): Boolean {
        return startDay.dayOfWeek == DayOfWeek.MONDAY
    }

    fun isWithEvents(): Boolean {
        return event != null
    }

    fun getIndexWeek(): Int {
        return startDay.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
    }

    fun getWeekTitle(): String? {
        val monthName = startDay.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val startWeek = startDay.dayOfMonth
        val endWeek = startWeek + 6
        val year = startDay.year
        return "$monthName $startWeek - $endWeek, $year"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(startDay.toString())
        parcel.writeString(endDay.toString())
        parcel.writeParcelable(event, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventDay> {
        override fun createFromParcel(parcel: Parcel): EventDay {
            return EventDay(parcel)
        }

        override fun newArray(size: Int): Array<EventDay?> {
            return arrayOfNulls(size)
        }
    }
}