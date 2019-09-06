package com.gsv28rus.calendar.event

import androidx.lifecycle.MutableLiveData
import com.gsv28rus.calendar.common.RequestHandler
import com.gsv28rus.calendar.common.mainThreadSubscribe
import com.gsv28rus.calendar.common.presentation.BaseViewModel
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import timber.log.Timber
import javax.inject.Inject

class EditEventViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        private val eventRepository: EventRepository
) : BaseViewModel(schedulerProvider) {
    lateinit var requestHandler: RequestHandler
    var eventDay: MutableLiveData<EventDay> = MutableLiveData()

    fun initEventDay(eventDay: EventDay?) {
        val eventDayValue: EventDay = getNewInstanceEventDay(eventDay)
        if (eventDayValue.event == null) {
            eventDayValue.event = Event(null, null, null, null, null, eventDayValue.startDay.minusHours(12), eventDayValue.startDay.minusHours(12), null, Repeat.NOT_REPEAT)
        }

        this.eventDay.value = eventDayValue
    }

    fun saveEvent() {
        requestHandler.startRequest()
        addDisposable(
            eventRepository.saveEvent(eventDay.value?.event!!)
                .mainThreadSubscribe(schedulerProvider)
                .subscribe({
                    requestHandler.endRequest()
                }, {
                    requestHandler.endRequest()
                    Timber.e(it)
                })
        )
    }

    fun updateStartTimeEvent(hourOfDay: Int, minute: Int) {
        val eventDayValue = eventDay.value
        eventDayValue?.event?.startDate = updateTime(eventDayValue?.event?.startDate, hourOfDay, minute)
        eventDay.value = eventDayValue
    }

    fun updateEndTimeEvent(hourOfDay: Int, minute: Int) {
        val eventDayValue = eventDay.value
        eventDayValue?.event?.endDate = updateTime(eventDayValue?.event?.endDate, hourOfDay, minute)
        eventDay.value = eventDayValue
    }

    fun updateStartDateEvent(year: Int, month: Int, dayOfMonth: Int) {
        val eventDayValue = eventDay.value
        eventDayValue?.event?.startDate = updateDate(eventDayValue?.event?.startDate, year, month, dayOfMonth)
        eventDay.value = eventDayValue
    }

    fun updateEndDateEvent(year: Int, month: Int, dayOfMonth: Int) {
        val eventDayValue = eventDay.value
        eventDayValue?.event?.endDate = updateDate(eventDayValue?.event?.endDate, year, month, dayOfMonth)
        eventDay.value = eventDayValue
    }

    private fun getNewInstanceEventDay(eventDay: EventDay?): EventDay {

        return if (eventDay == null) {
            val nowStartDay = ZonedDateTime.now(ZoneId.systemDefault())
            EventDay(nowStartDay, nowStartDay.plusDays(1), null)
        } else {
            eventDay.copy()
        }
    }

    private fun updateTime(zonedDateTime: ZonedDateTime?, hourOfDay: Int, minute: Int): ZonedDateTime {
        val now = LocalDate.now()
        if (zonedDateTime == null) {
            return ZonedDateTime.of(now.year, now.month.value, now.dayOfMonth, hourOfDay, minute, 0, 0, ZoneId.systemDefault())
        }
        return ZonedDateTime.of(zonedDateTime.year, zonedDateTime.month.value, zonedDateTime.dayOfMonth, hourOfDay, minute, zonedDateTime.second, zonedDateTime.nano, zonedDateTime.zone)
    }

    private fun updateDate(zonedDateTime: ZonedDateTime?, year: Int, month: Int, dayOfMonth: Int): ZonedDateTime {
        if (zonedDateTime == null) {
            return ZonedDateTime.of(year, month, dayOfMonth, 12, 0, 0, 0, ZoneId.systemDefault())
        }
        return ZonedDateTime.of(year, month, dayOfMonth, zonedDateTime.hour, zonedDateTime.minute, zonedDateTime.second, zonedDateTime.nano, zonedDateTime.zone)
    }
}