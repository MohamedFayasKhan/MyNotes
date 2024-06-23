package com.mohamedkhan.mynotes.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohamedkhan.mynotes.R
import com.mohamedkhan.mynotes.adapter.NotesAdapter
import com.mohamedkhan.mynotes.data.Note
import com.mohamedkhan.mynotes.data.NotesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var search: EditText
    private lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initialize()

        var adapter = NotesAdapter() {item->
            notesViewModel.deleteNote(item)
            Toast.makeText(this, "Notes deleted successfully", Toast.LENGTH_LONG).show()
        }

        search.addTextChangedListener{
            var value = it.toString()
            notesViewModel.searchNote("%"+value+"%").observe(this, Observer {notes ->
                adapter.setData(notes)
            })
        }

        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter

        notesViewModel.readNotes.observe(this, Observer { notes ->
            adapter.setData(notes)
        })

        fab.setOnClickListener{view ->
            var intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initialize() {
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        recycler = findViewById<RecyclerView>(R.id.recycler_notes_main)
        search = findViewById<EditText>(R.id.edit_search_main)
        fab = findViewById<FloatingActionButton>(R.id.fab_add_main)
    }
}