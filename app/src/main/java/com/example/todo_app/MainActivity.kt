package com.example.todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.todo_app.ui.AppNavHost
import com.example.todo_app.ui.theme.TODO_APPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODO_APPTheme {
                // Use Scaffold properly to wrap content
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    // Pass paddingValues to your navigation host or content
                    AppNavHost(navController = navController, modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}
