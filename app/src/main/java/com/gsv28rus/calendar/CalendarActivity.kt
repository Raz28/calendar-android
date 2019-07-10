package com.gsv28rus.calendar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.gsv28rus.calendar.common.presentation.BaseActivity
import com.gsv28rus.calendar.event.EventAdapter
import com.gsv28rus.calendar.event.EventViewModel
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class CalendarActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var eventViewModel: EventViewModel
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        setSupportActionBar(toolbar)
        initNavigationView()
        initFab()
        initList()
        initViewModel()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_list -> {
            }
            R.id.nav_day -> {

            }
            R.id.nav_week -> {

            }
            R.id.nav_month -> {

            }
            R.id.nav_dashboard -> {

            }
            R.id.nav_settings -> {

            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initNavigationView() {
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    private fun initFab() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
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
