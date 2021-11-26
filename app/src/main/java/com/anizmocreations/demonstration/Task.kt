package com.anizmocreations.demonstration

/**
 * This is a data class, there are a total of 3 fields in this class that constitute a task. Each
 * task when created is incomplete, hence the field completed is made to have a default argument of
 * false which can be overridden while creating a Task object or updating the value by a setter.
 */
data class Task(val taskName: String, var completed: Boolean = false)