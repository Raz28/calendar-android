package com.gsv28rus.calendar.event

import androidx.lifecycle.MutableLiveData
import com.gsv28rus.calendar.common.presentation.BaseViewModel
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class EditEventViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        private val eventRepository: EventRepository
) : BaseViewModel(schedulerProvider) {
    var eventDay: MutableLiveData<EventDay> = MutableLiveData()
    val allDay: MutableLiveData<Boolean> = MutableLiveData()

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