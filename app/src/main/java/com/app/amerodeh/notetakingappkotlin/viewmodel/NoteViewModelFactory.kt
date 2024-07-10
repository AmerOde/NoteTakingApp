package com.app.amerodeh.notetakingappkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.amerodeh.notetakingappkotlin.room.NoteRepository

class NoteViewModelFactory(val app:Application,private val repository: NoteRepository):ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return  NoteViewModel(app,repository)as T
    }

}