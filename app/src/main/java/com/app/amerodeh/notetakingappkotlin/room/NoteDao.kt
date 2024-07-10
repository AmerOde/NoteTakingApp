package com.app.amerodeh.notetakingappkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao  {
    @Insert
    suspend fun insertNote(note:Note):Long
    @Delete
    suspend fun deleteNote(note:Note)
    @Update
    suspend fun updateNote(note:Note)

    @Query("Select *From note ORDER BY id DESC")
    fun getAllNotesInDB(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE title LIKE :query OR body LIKE :query")
    fun findByNameNote(query:String?): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE title LIKE '%' || :query || '%' OR body LIKE '%' || :query || '%'")
    fun findByNameNoteCharacter(query:String?): LiveData<List<Note>>
}