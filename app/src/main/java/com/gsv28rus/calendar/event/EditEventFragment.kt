package com.gsv28rus.calendar.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gsv28rus.calendar.R
import com.gsv28rus.calendar.common.presentation.BaseFragment
import com.gsv28rus.calendar.databinding.FragmentEventEditBinding
import kotlinx.android.synthetic.main.app_bar_main.*

class EditEventFragment : BaseFragment() {
    private lateinit var binding: FragmentEventEditBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_edit, container, false)
        setHasOptionsMenu(true)
        activity?.toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }

        return binding.root
    }
}
