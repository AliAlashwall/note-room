package com.example.testroomdatabase.ui.screens.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testroomdatabase.data.database.Note
import com.example.testroomdatabase.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    private val repository = NoteRepository()
    private val _notesUiState: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notesUiState: StateFlow<List<Note>> = _notesUiState


    fun insertNote(noteContent: String) {
        viewModelScope.launch {
            repository.insertNote(
                Note(
                    id = 0,
                    content = noteContent,
                    date = "2024-01-01",
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