package com.gsv28rus.calendar.event

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gsv28rus.calendar.R
import com.gsv28rus.calendar.common.DatePickerType
import com.gsv28rus.calendar.common.presentation.BaseFragment
import com.gsv28rus.calendar.databinding.FragmentEventEditBinding

class EditEventFragment : BaseFragment() {
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
                val bundle = bundleOf("dateType" to DatePickerType.START_DATE)
                findNavController().navigate(R.id.action_editEventFragment_to_datePickerFragment, bundle)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initDataBinding() {
        editEventViewModel = activity?.run {
            ViewModelProviders.of(this, viewModelFactory).get(EditEventViewModel::class.java)
        }!!
        editEventViewModel.initEventDay(arguments?.getParcelable("eventDay"))
        binding.viewModel = editEventViewModel
        editEventViewModel.eventDay.observe(this, Observer {
            binding.eventDay = it
        })
    }

    private fun initUi() {
        binding.repeat.adapter = RepeatSpinnerAdapter(context)
        binding.startDate.setOnClickListener { findNavController().navigate(R.id.action_editEventFragment_to_datePickerFragment, bundleOf("dateType" to DatePickerType.START_DATE)) }
        binding.endDate.setOnClickListener { findNavController().navigate(R.id.action_editEventFragment_to_datePickerFragment, bundleOf("dateType" to DatePickerType.END_DATE)) }
        binding.startTime.setOnClickListener { findNavController().navigate(R.id.action_editEventFragment_to_timePickerFragment, bundleOf("dateType" to DatePickerType.START_DATE)) }
        binding.endTime.setOnClickListener { findNavController().navigate(R.id.action_editEventFragment_to_timePickerFragment, bundleOf("dateType" to DatePickerType.END_DATE)) }
    }
}
