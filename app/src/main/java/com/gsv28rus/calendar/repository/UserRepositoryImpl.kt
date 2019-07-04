package com.gsv28rus.calendar.repository

import com.gsv28rus.calendar.AppDatabase
import com.gsv28rus.calendar.api.UserApi
import com.gsv28rus.calendar.model.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val appDatabase: AppDatabase
) : UserRepository {

    override fun signUp(phone: String, password: String, passwordConfirmation: String): Completable {
        return userApi.signUp(phone, password, passwordConfirmation).toCompletable()
    }

    override fun signIn(phone: String, password: String): Completable {
        return userApi.signIn(phone, password).toCompletable()
    }

    override fun signOut(): Completable {
        return userApi.signOut().toCompletable()
    }

    override fun getUser(): Single<User> {
        return userApi.getUser().map { it.data }
    }

    override fun insertUser(user: User) {
        appDatabase.userDao().insert(user)
    }

    override fun updateUser(user: User) {
        appDatabase.userDao().update(user)
    }

    override fun deleteUser(user: User) {
        appDatabase.userDao().delete(user)
    }
}
