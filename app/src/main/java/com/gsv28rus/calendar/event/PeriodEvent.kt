package com.gsv28rus.calendar.event

import org.threeten.bp.ZonedDateTime

data class PeriodEvent(
    var startDate: ZonedDateTime,
    var endDate: ZonedDateTime
)