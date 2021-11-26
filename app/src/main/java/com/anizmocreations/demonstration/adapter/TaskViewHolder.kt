package com.anizmocreations.demonstration.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anizmocreations.demonstration.R

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val taskName = itemView.findViewById<TextView>(R.id.task_name)
    val taskDone = itemView.findViewById<CheckBox>(R.id.task_done)
}