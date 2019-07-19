package com.gsv28rus.calendar.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gsv28rus.calendar.R
import com.gsv28rus.calendar.common.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.*

class EventListFragment : BaseFragment() {
    private lateinit var eventListViewModel: EventListViewModel
    private lateinit var adapter: EventAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initList()
        initViewModel()
        initFab()
    }

    private fun initList() {
        adapter = EventAdapter(listOf(), listener = {
            val bundle = bundleOf("eventDay" to it)
            findNavController().navigate(R.id.action_eventListFragment_to_editEventFragment, bundle)
        })
        list.setHasFixedSize(true)
        list.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        list.adapter = adapter
    }

    private fun initViewModel() {
        eventListViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventListViewModel::class.java)
        eventListViewModel.getEventList().observe(this, Observer {
            adapter.setEventList(it)
        })
    }

    private fun initFab() {
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_eventListFragment_to_editEventFragment)
        }
    }
}
