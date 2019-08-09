package com.gsv28rus.calendar.user

import com.google.firebase.auth.FirebaseAuth
import com.gsv28rus.calendar.common.presentation.BaseViewModel
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import javax.inject.Inject

class UserViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider) {

    public fun signInAnonymously() {
        val auth = FirebaseAuth.getInstance()
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid?.let {
                        userRepository.insertUser(User(it))
                    }
                } else {
                }
            }
    }

}