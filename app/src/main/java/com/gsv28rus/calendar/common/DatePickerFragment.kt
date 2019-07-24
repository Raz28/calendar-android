package com.gsv28rus.calendar.common

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProviders
import com.gsv28rus.calendar.common.presentation.ViewModelFactory
import com.gsv28rus.calendar.event.EditEventViewModel
import dagger.android.support.DaggerDialogFragment
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import java.util.*
import javax.inject.Inject

class DatePickerFragment : DaggerDialogFragment(), DatePickerDialog.OnDateSetListener {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var editEventViewModel: EditEventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editEventViewModel = activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(EditEventViewModel::class.java)
        }!!
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        when (arguments?.get("dateType")) {
            DatePickerType.START_DATE -> editEventViewModel.setStartDateEvent(updateDate(editEventViewModel.eventDay.value?.event?.startDate, year, month, dayOfMonth))
            DatePickerType.END_DATE -> editEventViewModel.setEndDateEvent(updateDate(editEventViewModel.eventDay.value?.event?.endDate, year, month, dayOfMonth))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(context, this, year, month, day)
    }

    private fun updateDate(zonedDateTime: ZonedDateTime?, year: Int, month: Int, dayOfMonth: Int): ZonedDateTime {
        if (zonedDateTime == null) {
            return ZonedDateTime.of(year, month, dayOfMonth, 12, 0, 0, 0, ZoneId.systemDefault())
        }
        return ZonedDateTime.of(year, month, dayOfMonth, zonedDateTime.hour, zonedDateTime.minute, zonedDateTime.second, zonedDateTime.nano, zonedDateTime.zone)
    }
}
