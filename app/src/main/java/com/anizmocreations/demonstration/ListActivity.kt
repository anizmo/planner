package com.anizmocreations.demonstration

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.anizmocreations.demonstration.adapter.TaskListAdapter
import com.anizmocreations.demonstration.model.Task
import kotlinx.android.synthetic.main.activity_list.*

/**
 * Activity that houses the planner list, progress bar and create the option to add a new item to
 * the planner list.
 */
class ListActivity : AppCompatActivity(), TaskListListener {

    private var taskAdapter: TaskListAdapter? = null
    private var taskList : List<Task>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        taskList = ArrayList()

        if (Utils.getList(this).isNotEmpty()) {
            taskList = Utils.getList(this)
        }

        setupFloatingActionButton()

        setupEditText()

        setupRecyclerView()
        updateProgress()

    }

    /**
     * Setup up the IME_ACTION_DONE, this method is called when the user presses return
     * (enter) on the soft-keyboard.
     */
    private fun setupEditText() {
        edit_task_name.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            val newTaskName = edit_task_name.text.toString()
            if (actionId == EditorInfo.IME_ACTION_DONE && !TextUtils.isEmpty(newTaskName)) {
                (taskList as ArrayList).add(0, Task(newTaskName))
                taskAdapter?.notifyDataSetChanged()
                task_list.scrollToPosition(0)
                edit_task_name.text.clear()
                updateProgress()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun setupFloatingActionButton() {
        fab.setOnClickListener {
            if (add_new_item.visibility == View.VISIBLE) {
                edit_task_name.clearFocus()
                fab.rotation = 0f
                edit_task_name.text.clear()
                add_new_item.visibility = View.GONE
                hideKeyboard(edit_task_name)
            } else {
                edit_task_name.requestFocus()
                fab.rotation = 45f
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(edit_task_name, InputMethodManager.SHOW_FORCED)
                edit_task_name.setSelection(edit_task_name.getText().toString().length)
                add_new_item.visibility = View.VISIBLE
            }
            updateProgress()
        }
    }

    private fun setupRecyclerView() {
        task_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        taskAdapter = TaskListAdapter(taskList, this, this)

        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (taskList as ArrayList).removeAt(viewHolder.adapterPosition)
                taskAdapter?.notifyDataSetChanged()
                updateProgress()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(task_list)
        task_list.adapter = taskAdapter
    }

    override fun updateProgress() {
        var completed = 0
        //Calling Java code from a Kotlin file
        Utils.setList(taskList, this)
        taskList?.forEach { if(it.completed) { completed++ } }
        progress_indicator.progress =
            Utils.getPercentageFromTwoNumbers(completed, taskList?.size?:0)
        TransitionManager.beginDelayedTransition(parent_constraint_layout)
    }



}