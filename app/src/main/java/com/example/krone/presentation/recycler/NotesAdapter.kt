package com.example.krone.presentation.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.krone.data.Note
import com.example.krone.databinding.NoteItemBinding

class NotesAdapter: RecyclerView.Adapter<NotesViewHolder>() {

    private var data: List<Note> = emptyList()
    private var onItemClickListener: ((Note) -> Unit)? = null
    private var onPinClickListener: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        // Обработка нажатия на кнопку удаления
        holder.binding.vBtnRemove.setOnClickListener {
            onItemClickListener?.invoke(item)
        }

        // Обработка нажатия на кнопку закрепления
        holder.binding.vBtnPin.setOnClickListener {
            onPinClickListener?.invoke(item)
        }

        if (item.isPinned) {
            holder.binding.root.setBackgroundColor(Color.rgb(250, 128, 114))
        } else {
            holder.binding.root.setBackgroundColor(Color.rgb(218,207,126))
        }
    }

    fun updateData(newData: List<Note>) {
        this.data = newData.sortedByDescending { it.isPinned }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Note) -> Unit) {
        this.onItemClickListener = listener
    }

    fun setOnPinClickListener(listener: (Note) -> Unit) {
        this.onPinClickListener = listener
    }
}