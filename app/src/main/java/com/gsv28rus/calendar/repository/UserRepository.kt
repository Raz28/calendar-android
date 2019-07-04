package com.gsv28rus.calendar.repository

import com.gsv28rus.calendar.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
    fun signUp(phone: String, password: String, passwordConfirmation: String): Completable
    fun signIn(phone: String, password: String): Completable
    fun signOut(): Completable
    fun getUser(): Single<User>
    fun insertUser(user: User)
    fun updateUser(user: User)
    fun deleteUser(user: User)
}
