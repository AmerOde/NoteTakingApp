package com.app.amerodeh.notetakingappkotlin.viewmodel


import android.app.Application
import android.app.DownloadManager.Query
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.amerodeh.notetakingappkotlin.room.Note
import com.app.amerodeh.notetakingappkotlin.room.NoteRepository
import kotlinx.coroutines.launch



class NoteViewModel(app:Application,private val repository: NoteRepository) : AndroidViewModel(app){
    fun addNote(note :Note) =
        viewModelScope.launch {
            repository.insertNote(note)
        }


    fun deleteNote(note:Note) =
        viewModelScope.launch {
            repository.deleteNote(note)
        }

    fun updateNote(note:Note) =
        viewModelScope.launch {

            repository.updateNote(note)
        }

    fun getAllNotes() =
        repository.getAllNotes()

    fun searchNote (query:String) =
        repository.searchNote(query)
    fun searchNoteByCharacter(query:String) =repository.findByNameNoteCharacter(query)







}