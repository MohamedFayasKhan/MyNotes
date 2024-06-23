package com.mohamedkhan.mynotes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: Note)

    @Upsert
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM Notes ORDER BY modifiedTime DESC")
    fun readNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM Notes WHERE id = :id LIMIT 1")
    fun readNoteById(id: Int): Note

    @Query("SELECT * FROM Notes WHERE title LIKE :value OR description like :value ORDER BY modifiedTime DESC")
    fun searchNote(value: String): List<Note>
}