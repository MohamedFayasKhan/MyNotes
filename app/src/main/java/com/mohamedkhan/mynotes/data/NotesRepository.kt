package com.mohamedkhan.mynotes.data

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    val readNotes: LiveData<List<Note>> = notesDao.readNotes()

    suspend fun addNote(note: Note) {
        notesDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }
}