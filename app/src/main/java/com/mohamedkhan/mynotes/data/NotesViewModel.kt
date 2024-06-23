package com.mohamedkhan.mynotes.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val readNotes: LiveData<List<Note>>
    private val repository: NotesRepository

    init {
        val notesDao = NotesDatabase.getDatabase(application).notesDao()
        repository = NotesRepository(notesDao)
        readNotes = repository.readNotes
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun readNoteById(id: Int) = liveData(Dispatchers.IO) {
        val note = repository.readNoteById(id)
        emit(note)
    }

    fun searchNote(value: String) = liveData(Dispatchers.IO) {
        val notes = repository.searchNote(value)
        emit(notes)
    }
}