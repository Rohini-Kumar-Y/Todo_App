package com.example.todo_app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_app.ui.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    val tasks: LiveData<List<Task>> = taskDao.getTasks()

    fun addTask(task: Task) = viewModelScope.launch { taskDao.insertTask(task) }
    fun deleteTask(task: Task) = viewModelScope.launch { taskDao.deleteTask(task) }
}