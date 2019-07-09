package com.gsv28rus.calendar.event

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gsv28rus.calendar.R
import com.gsv28rus.calendar.common.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_event_list.*

class EventListActivity : BaseActivity() {

    private lateinit var eventViewModel: EventViewModel
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        initList()
        initViewModel()
    }

    private fun initList() {
        eventList.setHasFixedSize(true)
        eventList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = EventAdapter(listOf())
        eventList.adapter = adapter
    }

    private fun initViewModel() {
        eventViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventViewModel::class.java)
        eventViewModel.getEventList().observe(this, Observer {
            adapter.setEventList(it)
        })
    }
}
