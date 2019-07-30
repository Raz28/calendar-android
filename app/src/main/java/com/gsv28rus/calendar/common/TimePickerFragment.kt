package com.gsv28rus.calendar.common

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.lifecycle.ViewModelProviders
import com.gsv28rus.calendar.common.presentation.ViewModelFactory
import com.gsv28rus.calendar.event.EditEventViewModel
import dagger.android.support.DaggerDialogFragment
import org.threeten.bp.LocalTime
import javax.inject.Inject

class TimePickerFragment : DaggerDialogFragment(), TimePickerDialog.OnTimeSetListener {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var editEventViewModel: EditEventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editEventViewModel = activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(EditEventViewModel::class.java)
        }!!
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        when (arguments?.get("dateType")) {
            DatePickerType.START_DATE -> editEventViewModel.updateStartTimeEvent(hourOfDay, minute)
            DatePickerType.END_DATE -> editEventViewModel.updateEndTimeEvent(hourOfDay, minute)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val localTime = LocalTime.now()
        return TimePickerDialog(context, this, localTime.hour, localTime.minute, true)
    }
}