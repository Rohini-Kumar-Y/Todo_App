package com.example.todo_app.ui

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo_app.ui.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile private var instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, TaskDatabase::class.java, "task_db")
                    .build().also { instance = it }
            }
        }
    }
}