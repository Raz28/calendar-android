package com.gsv28rus.calendar.user

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GetTokenResult

interface UserRepository {
    fun insertUser(user: User)
    fun updateUser(user: User)
    fun getCurrentUser(): User?
    fun refreshToken(): Task<GetTokenResult>
}
