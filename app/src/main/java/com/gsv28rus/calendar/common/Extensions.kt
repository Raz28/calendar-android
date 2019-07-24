package com.gsv28rus.calendar.common

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.threeten.bp.ZonedDateTime

inline fun FragmentManager.executeTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()


inline fun <reified T> Single<T>.mainThreadSubscribe(schedulerProvider: SchedulerProvider): Single<T> {
    return this.observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
}

inline fun <reified T> Observable<T>.mainThreadSubscribe(schedulerProvider: SchedulerProvider): Observable<T> {
    return this.observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
}

fun Completable.mainThreadSubscribe(schedulerProvider: SchedulerProvider): Completable {
    return observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
}


fun ZonedDateTime.parseToHoursAndMinutes(): String {
    return "${"%02d".format(this.hour)}:${"%02d".format(this.minute)}"
}
