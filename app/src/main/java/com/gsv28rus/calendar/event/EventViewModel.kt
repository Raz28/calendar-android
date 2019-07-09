package com.gsv28rus.calendar.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gsv28rus.calendar.common.presentation.BaseViewModel
import com.gsv28rus.calendar.common.mainThreadSubscribe
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import timber.log.Timber
import javax.inject.Inject

class EventViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    private val eventRepository: EventRepository
) : BaseViewModel(schedulerProvider) {

    private var eventList: MutableLiveData<List<Event>>? = null

    fun getEventList(): LiveData<List<Event>> {
        if (eventList == null) {
            eventList = MutableLiveData()
            updateEventList()
        }

        return eventList as MutableLiveData<List<Event>>
    }

    private fun updateEventList() {
        addDisposable(
            eventRepository.getMockEvents()
                .mainThreadSubscribe(schedulerProvider)
                .subscribe({
                    eventList?.postValue(it)
                }, {
                    Timber.e(it)
                })
        )
    }
}