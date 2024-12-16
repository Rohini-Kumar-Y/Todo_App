package com.example.todo_roomdb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListPage(viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My To-Do List",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF81C784) // Light Green
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(viewModel, inputText) { updatedText ->
                inputText = updatedText
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF4A90E2), // Light Blue
                            Color(0xFF50E3C2)  // Light Teal
                        )
                    )
                )
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Input Field
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.2f)),
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = {
                    Text(
                        text = "Enter a task...",
                        color = Color.White.copy(alpha = 0.6f)
                    )
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    cursorColor = Color.White
                )
            )

            // Task List
            todoList?.let {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f) // Fill the remaining space
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        itemsIndexed(it) { _, item ->
                            TodoItem(item = item, onDelete = { viewModel.deleteTodo(item.id) })
                        }
                    }
                )
            } ?: Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                textAlign = TextAlign.Center,
                text = "No tasks yet. Add your first task!",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
        }
    }
}




@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF56CCF2), // Bright Sky Blue
                        Color(0xFF2F80ED)  // Deep Blue
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Task Details
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm aa, dd MMM", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color(0xFFE0E0E0), // Light Gray for contrast
                fontWeight = FontWeight.Light
            )
            Text(
                text = item.title,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        // Delete Button
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.delete_icon01),
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun FloatingActionButton(viewModel: TodoViewModel, inputText: String, onInputTextChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = {
                if (inputText.isNotBlank()) {
                    viewModel.addTodo(inputText)
                    onInputTextChange("")
                }
            },
            modifier = Modifier
                .size(56.dp) // Floating button size
                .clip(RoundedCornerShape(50))
                .background(Color(color = 0xFF56CCF2)
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_button01), // Replace with your '+' icon
                contentDescription = "Add Task",
                tint = Color.White,
                modifier = Modifier.size(24.dp) // Icon size
            )
        }
    }
}

