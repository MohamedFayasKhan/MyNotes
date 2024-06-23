package com.mohamedkhan.mynotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mohamedkhan.mynotes.R
import com.mohamedkhan.mynotes.data.Note
import com.mohamedkhan.mynotes.util.DateUtil

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    var notes = emptyList<Note>()

    class NotesHolder(itemView: View) : ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.title_notes_list_recycler)
        var description = itemView.findViewById<TextView>(R.id.description_notes_list_recycler)
        var date = itemView.findViewById<TextView>(R.id.date_notes_list_recycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        return NotesHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_list_recycler, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.title.text = notes.get(position).title
        holder.description.text = notes.get(position).description
        holder.date.text = DateUtil().epochToDate(notes.get(position).modifiedTime)
    }

    fun setData(notes: List<Note>) {
        this.notes = notes
    }
}