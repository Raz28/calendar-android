package com.gsv28rus.calendar.common

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.lifecycle.ViewModelProviders
import com.gsv28rus.calendar.common.presentation.ViewModelFactory
import com.gsv28rus.calendar.event.EditEventViewModel
import dagger.android.support.DaggerDialogFragment
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class TimePickerFragment : DaggerDialogFragment(), TimePickerDialog.OnTimeSetListener {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var editEventViewModel: EditEventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editEventViewModel = ViewModelProviders.of(this, viewModelFactory).get(EditEventViewModel::class.java)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        when (arguments?.get("dateType")) {
            DatePickerType.START_DATE -> updateTime(editEventViewModel.eventDay.value?.event?.startDate, hourOfDay, minute)
            DatePickerType.END_DATE -> updateTime(editEventViewModel.eventDay.value?.event?.endDate, hourOfDay, minute)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val localTime = LocalTime.now()
        return TimePickerDialog(context, this, localTime.hour, localTime.minute, true)
    }

    private fun updateTime(zonedDateTime: ZonedDateTime?, hourOfDay: Int, minute: Int): ZonedDateTime {
        val now = LocalDate.now()
        if (zonedDateTime == null) {
            return ZonedDateTime.of(now.year, now.month.value, now.dayOfMonth, hourOfDay, minute, 0, 0, ZoneId.systemDefault())
        }
        return ZonedDateTime.of(zonedDateTime.year, zonedDateTime.month.value, zonedDateTime.dayOfMonth, hourOfDay, minute, zonedDateTime.second, zonedDateTime.nano, zonedDateTime.zone)
    }
}