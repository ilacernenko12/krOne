package com.example.krone.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.krone.data.Note
import com.example.krone.databinding.NoteItemBinding

class NotesViewHolder(val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.apply {
            vTvNoteHeader.text = note.header
            vTvNoteBody.text = note.body
        }
    }
}