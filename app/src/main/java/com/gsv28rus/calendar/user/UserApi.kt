package com.gsv28rus.calendar.user

import io.reactivex.Single
import retrofit2.http.*

interface UserApi {

    @POST("/api/user/sign")
    fun signUp(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("password_confirmation") confirmPassword: String
    ): Single<String>

    @POST("/api/auth/login")
    fun signIn(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Single<String>

    @POST("/api/auth/logout")
    fun signOut(): Single<String>

    @PATCH("/api/user")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun editProfile(
        @Query("firstname") firstname: String?,
        @Query("lastname") lastname: String?,
        @Query("surname") surname: String?,
        @Query("email") email: String?
    ): Single<User>

    @GET("api/user")
    fun getUser(): Single<User>
}