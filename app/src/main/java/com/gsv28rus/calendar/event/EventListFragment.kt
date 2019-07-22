package com.gsv28rus.calendar.event

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.event_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_go_today -> true
            else -> item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(item)
        }
    }

    private fun initList() {
        adapter = EventAdapter(listOf(), listener = {
            findNavController().navigate(R.id.action_eventListFragment_to_editEventFragment,  bundleOf("eventDay" to it))
        })
        list.adapter = adapter
        list.setHasFixedSize(true)
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
