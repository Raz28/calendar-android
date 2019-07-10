package com.gsv28rus.calendar.event

import org.threeten.bp.DayOfWeek
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.TextStyle
import org.threeten.bp.temporal.WeekFields
import java.util.*

data class EventDay(
        var startDay: ZonedDateTime,
        var endDay: ZonedDateTime,
        var event: Event?
) {

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
}