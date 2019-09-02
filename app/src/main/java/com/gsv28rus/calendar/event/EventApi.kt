package com.gsv28rus.calendar.event

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface EventApi {

    @GET("/api/events")
    fun getAll(): Single<List<Event>>

    @GET("/api/events/{eventId}")
    fun getEvent(@Path("eventId") eventId: String): Single<Event>

    @PUT("/api/events/update")
    fun updateEvent(@Body event: Event?): Completable

    @GET("/api/events//delete/{eventId}")
    fun deleteEvent(@Path("eventId") eventId: String): Completable
}