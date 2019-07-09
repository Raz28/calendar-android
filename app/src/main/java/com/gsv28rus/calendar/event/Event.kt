package com.gsv28rus.calendar.event

import com.gsv28rus.calendar.common.parseToHoursAndMinutes
import org.threeten.bp.format.TextStyle
import java.util.*

data class Event(
        val id: String?,
        var userId: String?,
        var title: String?,
        var description: String?,
        var location: String?,
        val period: PeriodEvent?,
        var who: String?,
        var repeat: Repeat?
) {
    fun getDayOfMouth(): String? {
        return period?.startDate?.dayOfMonth?.toString()
    }

    fun getDayOfWeek(): String? {
        return period?.startDate?.dayOfWeek?.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    }

    fun getPeriod(): String? {
        return "${period?.startDate?.parseToHoursAndMinutes()} - ${period?.endDate?.parseToHoursAndMinutes()}"
    }
}