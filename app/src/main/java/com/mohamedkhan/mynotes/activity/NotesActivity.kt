package com.mohamedkhan.mynotes.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mohamedkhan.mynotes.R
import com.mohamedkhan.mynotes.constant.Constants
import com.mohamedkhan.mynotes.data.Note
import com.mohamedkhan.mynotes.data.NotesViewModel
import com.mohamedkhan.mynotes.util.DateUtil

class NotesActivity : AppCompatActivity() {

    private lateinit var imageBack: ImageView
    private lateinit var save: TextView
    private lateinit var createdTime: TextView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initialize()
        var edit: Int = intent.getIntExtra(Constants.INTENT_EDIT, -1)
        var isEdit: Boolean = if (edit != null && edit != -1) true else false
        var note1: Note? = null
        if (isEdit) {
            notesViewModel.readNoteById(edit).observe(this, Observer { note ->
                title.text = note.title
                description.text = note.description
                createdTime.text = DateUtil.epochToDate(note.createdTime)
                note1 = note
            })
        }
        save.setOnClickListener { view ->
            addOrUpdateNotes(isEdit, note1)
        }
        imageBack.setOnClickListener {
            finish()
        }
    }

    private fun addOrUpdateNotes(isEdit: Boolean, note: Note?) {
        var titleString: String? = title.text.toString()
        var descriptionString: String? = description.text.toString()
        var noteStatus = ""

        if (isEdit) {
            note?.title = titleString
            note?.description = descriptionString
            note?.modifiedTime = DateUtil.dateToEpoch(DateUtil.getCurrentFormattedTime())
            if (note != null && isDescriptionFilled()) {
                updateNote(note)
                noteStatus = "Note edit successfully"
            } else {
                noteStatus = "Don't leave description empty"
            }
        } else {
            if (isDescriptionFilled()) {
                addNote(titleString, descriptionString)
                noteStatus = "Note added successfully"
            } else {
                noteStatus = "Don't leave description empty"
            }
        }
        Toast.makeText(this, noteStatus, Toast.LENGTH_LONG).show()
        finish()
    }

    fun deleteNotes(note: Note) {
        notesViewModel.deleteNote(note)
        Toast.makeText(this, "Notes deleted successfully", Toast.LENGTH_LONG).show()
    }

    private fun addNote(titleString: String?, descriptionString: String?) {
        var note = Note(
            0,
            titleString,
            Constants.CATEGORY,
            descriptionString,
            DateUtil.dateToEpoch(DateUtil.getCurrentFormattedTime()),
            DateUtil.dateToEpoch(DateUtil.getCurrentFormattedTime())
        )
        notesViewModel.addNote(note)
    }

    private fun updateNote(note: Note) {
        notesViewModel.updateNote(note)
    }

    private fun isDescriptionFilled(): Boolean {
        return description.text.isNotEmpty()
    }


    private fun initialize() {
        imageBack = findViewById(R.id.image_back_notes)
        save = findViewById(R.id.text_save_notes)
        createdTime = findViewById(R.id.text_created_time_notes)
        title = findViewById(R.id.edit_title_notes)
        description = findViewById(R.id.edit_description_notes)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
    }
}