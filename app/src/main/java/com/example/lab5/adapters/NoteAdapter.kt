package com.example.lab5.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.R
import com.example.lab5.Note

class NoteAdapter(private val dishes: List<Note>, private val onItemClick: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.DishViewHolder>() {

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.note_title)
        private val shortDescription: TextView = itemView.findViewById(R.id.note_shortDescription)

        fun bind(note: Note) {
            title.text = note.title
            shortDescription.text = note.shortDescription
            itemView.setOnClickListener { onItemClick(note) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(dishes[position])
    }

    override fun getItemCount(): Int = dishes.size
}