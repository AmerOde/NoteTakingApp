package com.app.amerodeh.notetakingappkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.amerodeh.notetakingappkotlin.HomeFragmentDirections
import com.app.amerodeh.notetakingappkotlin.databinding.NoteLayoutBinding
import com.app.amerodeh.notetakingappkotlin.room.Note
import java.util.Random

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){
    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id && oldItem.body == newItem.body && oldItem.title == newItem.title

        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }


    }
    val differ  = AsyncListDiffer(this,differCallback)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))}

    override fun getItemCount(): Int {
           return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote =differ.currentList[position]
        holder.itemBinding.tvNoteTitle.text =currentNote.title
        holder.itemBinding.tvNoteBody.text =currentNote.body

        val random =Random()
        val color = Color.argb(255
            ,random.nextInt(256),
            random.nextInt(256)
            ,random.nextInt(256))

        holder.itemBinding.ibColor.setBackgroundColor(color)
        holder.itemView.setOnClickListener{

            val direction =HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            it.findNavController().navigate(direction)

        }






    }





    class NoteViewHolder(val itemBinding:NoteLayoutBinding) :RecyclerView.ViewHolder(itemBinding.root)




}