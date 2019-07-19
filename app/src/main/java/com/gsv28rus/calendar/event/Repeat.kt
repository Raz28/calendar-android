package com.gsv28rus.calendar.event

import androidx.annotation.StringRes
import com.gsv28rus.calendar.R

enum class Repeat(@StringRes var resId : Int) {
    NOT_REPEAT(R.string.repeat_not),
    DAILY(R.string.repeat_daily),
    WEEKLY(R.string.repeat_weekly),
    MONTHLY(R.string.repeat_monthly),
    YEARLY(R.string.repeat_yearly);
}