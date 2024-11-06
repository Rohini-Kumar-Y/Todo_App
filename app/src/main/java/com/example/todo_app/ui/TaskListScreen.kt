package com.example.todo_app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo_app.ui.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.observeAsState(emptyList())

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("To-Do List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddTask.route) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(tasks) { task ->
                ListItem(
                    headlineContent = { Text(task.title) },
                    supportingContent = { Text(task.description) },
                    trailingContent = {
                        IconButton(onClick = { viewModel.deleteTask(task) }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete Task")
                        }
                    }
                )
            }
        }
    }
}