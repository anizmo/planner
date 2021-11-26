package com.anizmocreations.demonstration.adapter

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anizmocreations.demonstration.R
import com.anizmocreations.demonstration.Task
import com.anizmocreations.demonstration.TaskListListener

/**
 * @param tasks             the list list of tasks to be displayed in the task recyclerview.
 * @param context           context for resource access and layout inflating.
 * @param taskListListener
 */
class TaskListAdapter(private val tasks: List<Task>?, private val context: Context,
                      private val taskListListener: TaskListListener) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(View.inflate(context, R.layout.item_student, null))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskName.text = tasks?.get(position)?.taskName
        holder.taskDone.isChecked = tasks?.get(position)?.completed == true

        holder.taskDone.setOnCheckedChangeListener { buttonView, isChecked ->
            tasks?.get(position)?.completed = isChecked
            taskListListener.updateProgress()
        }
    }

    override fun getItemCount(): Int {
        return tasks?.size?:0
    }

}