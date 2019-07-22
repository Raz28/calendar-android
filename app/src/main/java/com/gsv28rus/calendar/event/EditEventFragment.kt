package com.gsv28rus.calendar.event

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gsv28rus.calendar.R
import com.gsv28rus.calendar.common.DatePickerType
import com.gsv28rus.calendar.common.presentation.BaseFragment
import com.gsv28rus.calendar.databinding.FragmentEventEditBinding

class EditEventFragment : BaseFragment() {
    private lateinit var binding: FragmentEventEditBinding
    private lateinit var eventListViewModel: EventListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEventEditBinding.inflate(inflater, container, false)
        binding.eventDay = arguments?.getParcelable("eventDay")
        binding.save.setOnClickListener {
            val bundle = bundleOf("dateType" to DatePickerType.START_DATE)
            findNavController().navigate(R.id.action_editEventFragment_to_datePickerFragment, bundle)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initViewModel()
        initSpinner()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.event_edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewModel() {
        eventListViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventListViewModel::class.java)
        binding.viewModel = eventListViewModel
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
    }

    private fun initSpinner() {
        binding.repeat.adapter = RepeatSpinnerAdapter(context)
    }
}
