package com.gsv28rus.calendar.event

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gsv28rus.calendar.R
import com.gsv28rus.calendar.common.DATE_TYPE
import com.gsv28rus.calendar.common.DatePickerType
import com.gsv28rus.calendar.common.EVENT_DAY
import com.gsv28rus.calendar.common.RequestHandler
import com.gsv28rus.calendar.common.presentation.BaseFragment
import com.gsv28rus.calendar.databinding.FragmentEventEditBinding

class EditEventFragment : BaseFragment(), RequestHandler {
    private lateinit var binding: FragmentEventEditBinding
    private lateinit var editEventViewModel: EditEventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEventEditBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        initDataBinding()
        initUi()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.event_edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                editEventViewModel.saveEvent()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun startRequest() {
        findNavController().navigate(R.id.preloaderFragment)
    }

    override fun endRequest() {
        findNavController().popBackStack()
    }

    private fun initDataBinding() {
        editEventViewModel = activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(EditEventViewModel::class.java)
        }!!
        editEventViewModel.initEventDay(arguments?.getParcelable(EVENT_DAY))
        editEventViewModel.requestHandler = this
        binding.viewModel = editEventViewModel
        editEventViewModel.eventDay.observe(this, Observer {
            binding.eventDay = it
        })
    }

    private fun initUi() {
        binding.repeat.adapter = RepeatSpinnerAdapter(context)
        binding.startDate.setOnClickListener { findNavController().navigate(R.id.action_editEventFragment_to_datePickerFragment, bundleOf(DATE_TYPE to DatePickerType.START_DATE)) }
        binding.endDate.setOnClickListener { findNavController().navigate(R.id.action_editEventFragment_to_datePickerFragment, bundleOf(DATE_TYPE to DatePickerType.END_DATE)) }
        binding.startTime.setOnClickListener { findNavController().navigate(R.id.action_editEventFragment_to_timePickerFragment, bundleOf(DATE_TYPE to DatePickerType.START_DATE)) }
        binding.endTime.setOnClickListener { findNavController().navigate(R.id.action_editEventFragment_to_timePickerFragment, bundleOf(DATE_TYPE to DatePickerType.END_DATE)) }
    }
}
