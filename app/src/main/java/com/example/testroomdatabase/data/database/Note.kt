package com.example.testroomdatabase.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val content: String,
    val date: Date,
    val isImportant: Boolean
)
