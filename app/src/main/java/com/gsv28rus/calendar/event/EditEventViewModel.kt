package com.gsv28rus.calendar.event

import androidx.lifecycle.MutableLiveData
import com.gsv28rus.calendar.common.presentation.BaseViewModel
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import javax.inject.Inject

class EditEventViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    private val eventRepository: EventRepository
) : BaseViewModel(schedulerProvider) {
    var eventDay: MutableLiveData<EventDay> = MutableLiveData()
    val allDay: MutableLiveData<Boolean> = MutableLiveData()

    init {
        allDay.postValue(false)
    }
}