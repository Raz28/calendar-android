package com.gsv28rus.calendar.common

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProviders
import com.gsv28rus.calendar.common.presentation.ViewModelFactory
import com.gsv28rus.calendar.event.EditEventViewModel
import dagger.android.support.DaggerDialogFragment
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
            DatePickerType.START_DATE -> editEventViewModel.updateStartDateEvent(year, month, dayOfMonth)
            DatePickerType.END_DATE -> editEventViewModel.updateEndDateEvent(year, month, dayOfMonth)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(context, this, year, month, day)
    }
}
