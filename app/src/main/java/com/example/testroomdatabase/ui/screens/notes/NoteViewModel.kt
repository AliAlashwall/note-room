package com.example.testroomdatabase.ui.screens.notes

import androidx.lifecycle.ViewModel
import com.example.testroomdatabase.data.database.Note
import com.example.testroomdatabase.data.repository.NoteRepository

class NoteViewModel : ViewModel() {

    private val repository = NoteRepository()

    fun insertNote(/*note: Note*/) {
        repository.insertNote(
            Note(
                id = 0,
                content = "Hello, Ali!",
                date = "2024-01-01",
                isImportant = false
            )
        )
    }

    fun getNotes() = repository.getNotes()
}