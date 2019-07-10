package com.gsv28rus.calendar.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gsv28rus.calendar.common.mainThreadSubscribe
import com.gsv28rus.calendar.common.presentation.BaseViewModel
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.ZoneId
import timber.log.Timber
import javax.inject.Inject

class EventViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        private val eventRepository: EventRepository
) : BaseViewModel(schedulerProvider) {

    private var eventList: MutableLiveData<List<EventDay>>? = null

    fun getEventList(): LiveData<List<EventDay>> {
        if (eventList == null) {
            eventList = MutableLiveData()
            updateEventList()
        }

        return eventList as MutableLiveData<List<EventDay>>
    }

    private fun updateEventList() {
        addDisposable(
                eventRepository.getMockEvents()
                        .mainThreadSubscribe(schedulerProvider)
                        .map { events -> updateEventToAllDay(events) }
                        .subscribe({
                            eventList?.postValue(it)
                        }, {
                            Timber.e(it)
                        })
        )
    }

    private fun updateEventToAllDay(events: List<Event>): MutableList<EventDay> {
        val nowYearMonth: YearMonth = YearMonth.now()
        val days = mutableListOf<EventDay>()
        for (month in nowYearMonth.month.ordinal + 1..12) {
            val yearMonth = YearMonth.of(nowYearMonth.year, month)

            for (day in 1..yearMonth.lengthOfMonth()) {
                val startDay = LocalDate.of(nowYearMonth.year, month, day).atStartOfDay().atZone(ZoneId.systemDefault())
                val endDay = startDay.plusDays(1)

                val event = events.find { event ->
                    event.startDate!!.isAfter(startDay) && event.startDate!!.isBefore(endDay)
                }

                days.add(EventDay(startDay, endDay, event))
            }
        }

        return days
    }
}