package com.example.testroomdatabase.data.repository

import com.example.testroomdatabase.data.database.Note
import com.example.testroomdatabase.data.database.NoteDatabase

class NoteRepository {

    private val dao = NoteDatabase.getInstanceWithoutContext().getNoteDao()

    fun insertNote(note: Note) {
        dao.insert(note)
    }

    fun getNotes() = dao.getAllNotes()

}