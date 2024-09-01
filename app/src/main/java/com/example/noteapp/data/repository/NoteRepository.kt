package com.example.noteapp.data.repository

import com.example.noteapp.data.database.Note
import com.example.noteapp.data.database.NoteDatabase

class NoteRepository {

    private val dao = NoteDatabase.getInstanceWithoutContext().getNoteDao()

    suspend fun insertNote(note: Note) {
        dao.insert(note)
    }

    fun getNotes() = dao.getAllNotes()

    suspend fun deleteNote(note: Note) {
        dao.delete(note)
    }
}