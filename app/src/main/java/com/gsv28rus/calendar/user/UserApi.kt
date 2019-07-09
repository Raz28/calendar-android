package com.gsv28rus.calendar.user

import com.gsv28rus.calendar.common.ApiResponse
import com.gsv28rus.calendar.user.User
import io.reactivex.Single
import retrofit2.http.*

interface UserApi {

    @POST("/api/user/sign")
    fun signUp(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("password_confirmation") confirmPassword: String
    ): Single<ApiResponse<String>>

    @POST("/api/auth/login")
    fun signIn(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Single<ApiResponse<String>>

    @POST("/api/auth/logout")
    fun signOut(): Single<ApiResponse<String>>

    @PATCH("/api/user")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun editProfile(
        @Query("firstname") firstname: String?,
        @Query("lastname") lastname: String?,
        @Query("surname") surname: String?,
        @Query("email") email: String?
    ): Single<ApiResponse<User>>

    @GET("api/user")
    fun getUser(): Single<ApiResponse<User>>
}