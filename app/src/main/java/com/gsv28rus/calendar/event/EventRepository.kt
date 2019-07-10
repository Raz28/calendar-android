package com.gsv28rus.calendar.event

import io.reactivex.Single

interface EventRepository {
    fun getAll(): Single<List<Event>>
    fun getMockEvents():  Single<List<Event>>
}
