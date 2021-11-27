package com.anizmocreations.demonstration

/**
 * Callback interface used for the recycler view items to interact with the activity from which it
 * is called. This interface is implemented in the activity from which the adapter of the recycler
 * view is assigned.
 */
interface TaskListListener {

    /**
     * Updates the progress as per the number of tasks completed in the task list.
     */
    fun updateProgress()

}