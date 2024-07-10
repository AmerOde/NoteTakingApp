package com.app.amerodeh.notetakingappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.app.amerodeh.notetakingappkotlin.databinding.ActivityMainBinding
import com.app.amerodeh.notetakingappkotlin.room.NoteDatabase
import com.app.amerodeh.notetakingappkotlin.room.NoteRepository
import com.app.amerodeh.notetakingappkotlin.viewmodel.NoteViewModel
import com.app.amerodeh.notetakingappkotlin.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel :NoteViewModel
    lateinit var binding :ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val noteRepository =NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory =NoteViewModelFactory(application,noteRepository)
        noteViewModel =ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}