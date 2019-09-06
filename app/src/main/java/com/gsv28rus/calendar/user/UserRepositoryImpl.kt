package com.gsv28rus.calendar.user

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.gsv28rus.calendar.AppDatabase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val appDatabase: AppDatabase
) : UserRepository {

    override fun getCurrentUser(): User? {
        return appDatabase.userDao().getCurrentUser()
    }

    override fun insertUser(user: User) {
        appDatabase.userDao().insert(user)
    }

    override fun updateUser(user: User) {
        appDatabase.userDao().update(user)
    }

    override fun refreshToken(): Task<GetTokenResult> {
        return FirebaseAuth.getInstance()
            .signInAnonymously()
            .continueWithTask { task -> task.result?.user?.getIdToken(false) }
            .addOnSuccessListener {
                insertUser(User(FirebaseAuth.getInstance().currentUser!!.uid, it.token!!))
            }
    }
}
