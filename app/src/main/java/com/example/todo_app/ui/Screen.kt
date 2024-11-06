package com.example.todo_app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo_app.ui.AddTaskScreen
import com.example.todo_app.ui.TaskListScreen

sealed class Screen(val route: String) {
    object TaskList : Screen("taskList")
    object AddTask : Screen("addTask")
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.TaskList.route) {
        composable(Screen.TaskList.route) { TaskListScreen(navController) }
        composable(Screen.AddTask.route) { AddTaskScreen(navController) }
    }
}