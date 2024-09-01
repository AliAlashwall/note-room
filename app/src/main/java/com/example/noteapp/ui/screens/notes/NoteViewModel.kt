package com.example.noteapp.ui.screens.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.database.Note
import com.example.noteapp.data.repository.NoteRepository
import com.example.noteapp.ui.screens.notes.component.model.NoteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class NoteViewModel : ViewModel() {

    private val repository = NoteRepository()
    private val _notesUiState: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notesUiState: StateFlow<List<Note>> = _notesUiState

    private val _noteUiState: MutableStateFlow<NoteUiState> = MutableStateFlow(NoteUiState())
    val noteUiState: StateFlow<NoteUiState> = _noteUiState


    fun updateNoteTitle(title: String) {
        _noteUiState.value = _noteUiState.value.copy(noteTitle = title)
    }

    fun updateNoteContent(content: String) {
        _noteUiState.value = _noteUiState.value.copy(noteContent = content)
    }

    fun insertNote(noteTitle: String, noteContent: String) {
        viewModelScope.launch {
            repository.insertNote(
                Note(
                    id = 0,
                    title = noteTitle,
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