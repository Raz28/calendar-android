package com.gsv28rus.calendar.event

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface EventApi {

    @GET("/api/events/all-in")
    fun getAllIn(): Single<List<Event>>

    @GET("/api/events/mock")
    fun getMock(): Single<List<Event>>

    @GET("/api/events/all")
    fun getAll(
        @Query("userId") userId: String
    ): Observable<Event>

    @GET("/api/events/{eventId}")
    fun getEvent(@Path("eventId") eventId: String): Single<Event>

    @PUT("/api/events/update")
    fun updateEvent(@Body event: Event?): Completable

    @GET("/api/events//delete/{eventId}")
    fun deleteEvent(@Path("eventId") eventId: String): Completable
}