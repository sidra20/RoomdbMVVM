package com.sidrakotlin.notepad.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sidrakotlin.notepad.AppDatabase
import com.sidrakotlin.notepad.adapters.NoteAdapter
import com.sidrakotlin.notepad.databinding.LayoutAllnotesBinding
import com.sidrakotlin.notepad.datamodels.Note
import com.sidrakotlin.notepad.viewmodels.AppViewModel

class NotesActivity:AppCompatActivity(), NoteAdapter.ChangesToNote {
    private lateinit var binding: LayoutAllnotesBinding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var note: Note
    private lateinit var noteList: List<Note>
    private lateinit var viewModel:AppViewModel
    private lateinit var mydb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutAllnotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteList = ArrayList()
        mydb = AppDatabase.getInstance(this)
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        viewModel.notesObserver().observe(this, Observer {
            noteAdapter.notifyDataSetChanged()
        })
        binding.notesRv.layoutManager = LinearLayoutManager(this);
        binding.floatAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)

        }
        getNotesList()

    }

    private fun getNotesList()
    {

        noteList = mydb.noteDao().getAll()!!
        if(noteList.isEmpty())
        {
            Toast.makeText(this, "No notes yet!", Toast.LENGTH_SHORT).show()

        }
        noteAdapter= NoteAdapter(this@NotesActivity, noteList!!,this,  )
        binding.notesRv.adapter=noteAdapter

    }

    override fun deleteNote(note: Note) {

        viewModel.deleteNote(note)
        noteAdapter.notifyDataSetChanged()
        val intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Deleted note!", Toast.LENGTH_SHORT).show()

    }

    override fun updateNote(note: Note) {
        TODO("Not yet implemented")
    }
}