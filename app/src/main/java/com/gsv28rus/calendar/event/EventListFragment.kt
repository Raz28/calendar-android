package com.gsv28rus.calendar.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.gsv28rus.calendar.R
import com.gsv28rus.calendar.common.presentation.BaseFragment
import com.gsv28rus.calendar.databinding.FragmentListBinding

class EventListFragment : BaseFragment() {
    private lateinit var eventViewModel: EventViewModel
    private lateinit var adapter: EventAdapter
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        initList()
        initViewModel()
        initFab()
        return binding.root
    }

    private fun initList() {
        adapter = EventAdapter(listOf())
        with(binding) {
            list.setHasFixedSize(true)
            list.layoutManager = LinearLayoutManager(activity)
            list.adapter = adapter
        }
    }

    private fun initViewModel() {
        eventViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventViewModel::class.java)
        eventViewModel.getEventList().observe(this, Observer {
            adapter.setEventList(it)
        })
    }

    private fun initFab() {
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
