package com.example.noteapp.ui.screens.notes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.database.Note
import com.example.noteapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class NoteViewModel : ViewModel() {

    private val repository = NoteRepository()
    private val _notesUiState: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notesUiState: StateFlow<List<Note>> = _notesUiState


    @RequiresApi(Build.VERSION_CODES.O)
    fun insertNote(noteContent: String) {
        viewModelScope.launch {
            repository.insertNote(
                Note(
                    id = 0,
                    content = noteContent,
                    date = Date(),
                    isImportant = false
                )
            )
            getNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun getNotes() {
        viewModelScope.launch {
            repository.getNotes().collect {
                _notesUiState.value = it
            }
        }
    }
}