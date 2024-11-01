package com.example.noteapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val content: String,
    val date: Date,
    val isImportant: Boolean,
    val color: Long
)
