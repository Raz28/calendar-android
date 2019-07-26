package com.gsv28rus.calendar.event

import androidx.lifecycle.MutableLiveData
import com.gsv28rus.calendar.common.presentation.BaseViewModel
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class EditEventViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        private val eventRepository: EventRepository
) : BaseViewModel(schedulerProvider) {
    var eventDay: MutableLiveData<EventDay> = MutableLiveData()
    val allDay: MutableLiveData<Boolean> = MutableLiveData()

    fun initEventDay(eventDay: EventDay?) {
        lateinit var eventDayValue: EventDay
        if (eventDay == null) {
            val nowStartDay = ZonedDateTime.now(ZoneId.systemDefault())
            eventDayValue = EventDay(nowStartDay, nowStartDay.plusDays(1), null)
        } else {
            eventDayValue = eventDay.copy()
        }

        if (eventDayValue.event == null) {
            eventDayValue.event = Event(null, null, null, null, null, eventDayValue.startDay.minusHours(12), eventDayValue.startDay.minusHours(12), null, Repeat.NOT_REPEAT)
        }

        this.eventDay.value = eventDayValue
    }

    fun setStartDateEvent(startDate: ZonedDateTime) {
        val eventDayValue = eventDay.value
        eventDayValue?.event?.startDate = startDate
        eventDay.postValue(eventDayValue)
    }

    fun setEndDateEvent(endDate: ZonedDateTime) {
        val eventDayValue = eventDay.value
        eventDayValue?.event?.endDate = endDate
        eventDay.postValue(eventDayValue)
    }
}