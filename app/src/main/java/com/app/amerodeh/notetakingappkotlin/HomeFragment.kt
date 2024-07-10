package com.app.amerodeh.notetakingappkotlin

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView

import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.amerodeh.notetakingappkotlin.adapter.NoteAdapter
import com.app.amerodeh.notetakingappkotlin.databinding.FragmentHomeBinding
import com.app.amerodeh.notetakingappkotlin.room.Note
import com.app.amerodeh.notetakingappkotlin.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home),SearchView.OnQueryTextListener {
    private var _binding:FragmentHomeBinding? =null
    private val binding get() = _binding

   lateinit var notesViewModel :NoteViewModel
    private lateinit var noteAdapter: NoteAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel =(activity as MainActivity).noteViewModel
        setUpRecyclerView()

        binding?.fabAddNote?.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)


        }
    }

    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter()
        binding!!.rvHomeFragment.apply {
            layoutManager =StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter =noteAdapter

        }
        activity?.let {
            notesViewModel.getAllNotes().observe(
                viewLifecycleOwner ,{
                    note-> noteAdapter.differ.submitList(note)
                    updateUI(note)
                }
            )
        }


    }

    private fun updateUI(note: List<Note>?) {
        if (note != null) {
            if(note.isNotEmpty()){
                binding?.cardView?.visibility =View.GONE
                binding?.rvHomeFragment?.visibility =View.VISIBLE



            }
            else{

                binding?.cardView?.visibility =View.VISIBLE
                binding?.rvHomeFragment?.visibility =View.GONE

            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)
        val mMenuSearch =menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled =false
        mMenuSearch.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
            searchNote(query)

        return false

    }



    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!=null){
            searchNoteByCharacter(newText)
        }
        return true

    }
    private fun searchNote(query: String?) {

        val searchQuery ="%$query"
        notesViewModel.searchNote(searchQuery).observe(

            this,{list->noteAdapter.differ.submitList(list)}


        )

    }
    private fun searchNoteByCharacter(query: String?) {

        val searchQuery ="$query"
        notesViewModel.searchNoteByCharacter(searchQuery).observe(

            this,{list->noteAdapter.differ.submitList(list)}


        )

    }

    override fun onDestroy() {
        super.onDestroy()

        _binding =null
    }


}
