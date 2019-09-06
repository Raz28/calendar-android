package com.gsv28rus.calendar.event

import io.reactivex.Completable
import io.reactivex.Single

interface EventRepository {
    fun getAll(): Single<List<Event>>
    fun saveEvent(event: Event): Completable
}
