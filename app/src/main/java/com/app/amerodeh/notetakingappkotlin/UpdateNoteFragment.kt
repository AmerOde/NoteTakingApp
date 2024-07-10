package com.app.amerodeh.notetakingappkotlin

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.app.amerodeh.notetakingappkotlin.databinding.FragmentUpdateNoteBinding
import com.app.amerodeh.notetakingappkotlin.room.Note
import com.app.amerodeh.notetakingappkotlin.viewmodel.NoteViewModel


class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
    private  var _binding :FragmentUpdateNoteBinding ?=null
    private val binding get() =_binding
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var mView :View

    private lateinit var currentNote:Note

    //Since the Update Note Fragment contains argument in nav_graph
    private  val args: UpdateNoteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel =(activity as MainActivity).noteViewModel
        mView =view
        currentNote =args.note!!



        binding?.etNoteTitleUpdate?.setText(currentNote.title)
        binding?.etNoteBodyUpdate?.setText(currentNote.body)





        // when update the note
        binding?.fabDone?.setOnClickListener{
            updateNote(mView)

        }
    }

    private fun updateNote(mView: View) {
        val title= binding?.etNoteTitleUpdate?.text.toString().trim()
        val body =binding?.etNoteBodyUpdate?.text.toString().trim()


        if(title.isNotEmpty()){

            val note = Note(currentNote.id,title,body)
            notesViewModel.updateNote(note)
            Toast.makeText(mView.context,"Update Note Successfully",Toast.LENGTH_SHORT).show()
            mView.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
        }
        else{
            Toast.makeText(mView.context,"Title is Empty!",Toast.LENGTH_SHORT).show()


        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.delete_menu_note,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.btn_delete_menu-> deleteNote(mView)

        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteNote(mView: View) {

        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("You want to delete this note?")
            setPositiveButton("Delete"){_,_ ->
                notesViewModel.deleteNote(currentNote)
                mView.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)

            }
            setNegativeButton("Cancel",null)


        }.create().show()





    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }

}