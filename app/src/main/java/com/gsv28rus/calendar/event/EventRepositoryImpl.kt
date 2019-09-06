package com.gsv28rus.calendar.event

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class EventRepositoryImpl
@Inject constructor(private val eventApi: EventApi) : EventRepository {

    override fun getAll(): Single<List<Event>> {
        return eventApi.getAll()
    }

    override fun saveEvent(event: Event): Completable {
        return eventApi.saveEvent(event)
    }
}
