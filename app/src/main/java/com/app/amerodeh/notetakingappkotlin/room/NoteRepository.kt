package com.app.amerodeh.notetakingappkotlin.room

import androidx.lifecycle.LiveData


class NoteRepository(private val db :NoteDatabase) {



    suspend fun insertNote(note:Note) =db.getNoteDao().insertNote(note)


   suspend fun deleteNote(note:Note) =db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note:Note){
        return db.getNoteDao().updateNote(note)

    }

    fun searchNote(query:String): LiveData<List<Note>> {
        return db.getNoteDao().findByNameNote(query)
    }
    fun getAllNotes() =db.getNoteDao().getAllNotesInDB()

    fun findByNameNoteCharacter(query:String?): LiveData<List<Note>> =db.getNoteDao().findByNameNoteCharacter(query)






}