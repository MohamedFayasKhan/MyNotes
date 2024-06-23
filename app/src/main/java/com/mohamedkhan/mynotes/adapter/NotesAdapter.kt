package com.mohamedkhan.mynotes.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mohamedkhan.mynotes.R
import com.mohamedkhan.mynotes.activity.NotesActivity
import com.mohamedkhan.mynotes.constant.Constants
import com.mohamedkhan.mynotes.data.Note
import com.mohamedkhan.mynotes.util.DateUtil

class NotesAdapter(private val listener: (Note) -> Unit): RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    var notes = emptyList<Note>()

    class NotesHolder(itemView: View) : ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.title_notes_list_recycler)
        var description = itemView.findViewById<TextView>(R.id.description_notes_list_recycler)
        var date = itemView.findViewById<TextView>(R.id.date_notes_list_recycler)
        var card = itemView.findViewById<ConstraintLayout>(R.id.constraint_notes_list_recycler)

        fun bind(item: Note, listener: (Note) -> Unit) {
            // Set click listener
            card.setOnLongClickListener{
                listener(item)
                true
            }
        }
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
        holder.date.text = DateUtil.epochToDate(notes.get(position).modifiedTime)
        holder.bind(notes[position], listener)
        holder.card.setOnClickListener{
            var intent = Intent(it.context, NotesActivity::class.java)
            intent.putExtra(Constants.INTENT_EDIT, notes.get(position).id)
            it.context.startActivity(intent)
        }
    }

    fun setData(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}