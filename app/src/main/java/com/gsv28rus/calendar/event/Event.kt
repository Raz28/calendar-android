package com.gsv28rus.calendar.event

import com.gsv28rus.calendar.common.parseToHoursAndMinutes
import org.threeten.bp.ZonedDateTime

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
) {

    fun getPeriod(): String? {
        return "${startDate?.parseToHoursAndMinutes()} - ${endDate?.parseToHoursAndMinutes()}"
    }
}