package com.app.amerodeh.notetakingappkotlin

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.app.amerodeh.notetakingappkotlin.databinding.FragmentNewNoteBinding
import com.app.amerodeh.notetakingappkotlin.room.Note
import com.app.amerodeh.notetakingappkotlin.viewmodel.NoteViewModel


class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
    private var _binding : FragmentNewNoteBinding? =null
    private val binding  get() =_binding
    lateinit var notesViewModel: NoteViewModel
    private lateinit var mView:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentNewNoteBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel =(activity as MainActivity).noteViewModel
        mView =view


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.new_menu_note,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.btn_save_menu-> saveNote(mView)

        }
        return super.onOptionsItemSelected(item)

    }

    private fun saveNote(view:View) {
        val title =binding?.etNoteTitle?.text.toString().trim()
        val body = binding?.etNoteBody?.text.toString().trim()

        if(title.isNotEmpty()){
            val note =Note(0,title,body)
            notesViewModel.addNote(note)
            Toast.makeText(view.context,"Save Note Successfully",Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)


        }
        else{
            Toast.makeText(view.context,"The Title is Empty !",Toast.LENGTH_SHORT).show()

        }



    }
    override fun onDestroy() {
        super.onDestroy()

        _binding =null
    }
}