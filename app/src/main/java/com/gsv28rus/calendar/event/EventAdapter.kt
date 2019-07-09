package com.gsv28rus.calendar.event

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gsv28rus.calendar.R
import com.gsv28rus.calendar.common.inflate
import com.gsv28rus.calendar.databinding.EventItemBinding

class EventAdapter(private var eventList: List<Event>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.event_item))
    override fun getItemCount(): Int = eventList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(eventList[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var binding: EventItemBinding

        fun bind(item: Event) = with(itemView) {
            binding = DataBindingUtil.bind(itemView)!!
            with(binding) {
                event = item
            }
        }
    }

    fun setEventList(eventList: List<Event>) {
        this.eventList = eventList
        notifyDataSetChanged()
    }
}