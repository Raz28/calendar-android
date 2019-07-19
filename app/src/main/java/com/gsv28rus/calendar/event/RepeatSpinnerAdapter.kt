package com.gsv28rus.calendar.event

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.gsv28rus.calendar.common.inflate


class RepeatSpinnerAdapter(context: Context?) : ArrayAdapter<Repeat>(context, 0, Repeat.values()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = parent.inflate(android.R.layout.simple_spinner_item)
        val text = layout.findViewById<TextView>(android.R.id.text1)
        text.text = context.resources.getString(getItem(position)!!.resId)
        return text
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = parent.inflate(android.R.layout.simple_spinner_item)
        val text = layout.findViewById<TextView>(android.R.id.text1)
        text.text = context.resources.getString(getItem(position)!!.resId)
        return text
    }
}