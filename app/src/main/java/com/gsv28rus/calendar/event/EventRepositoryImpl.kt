package com.gsv28rus.calendar.event

import io.reactivex.Single
import javax.inject.Inject

class EventRepositoryImpl
@Inject constructor(private val eventApi: EventApi) : EventRepository {

    override fun getAll(): Single<List<Event>> {
        return eventApi.getAllIn()
    }

    override fun getMockEvents(): Single<List<Event>> {
        return eventApi.getMock()
    }

    fun getEventById(eventId: String): Single<Event> {
        return eventApi.getEvent(eventId)
    }
}
