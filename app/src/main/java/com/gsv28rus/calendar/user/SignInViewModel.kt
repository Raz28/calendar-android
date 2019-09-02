package com.gsv28rus.calendar.user

import com.google.firebase.auth.FirebaseAuth
import com.gsv28rus.calendar.common.RequestHandler
import com.gsv28rus.calendar.common.presentation.BaseViewModel
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import javax.inject.Inject


class SignInViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider) {
    lateinit var requestHandler: RequestHandler

    fun signInAnonymously() {
        if (userRepository.getCurrentUser() != null) {
            return
        }
        requestHandler.startRequest()

        FirebaseAuth.getInstance()
            .signInAnonymously()
            .continueWithTask { task -> task.result?.user?.getIdToken(false) }
            .addOnSuccessListener {
                userRepository.insertUser(User(FirebaseAuth.getInstance().currentUser!!.uid, it.token!!))
                requestHandler.endRequest()
            }
    }

}