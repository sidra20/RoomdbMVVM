package com.sidrakotlin.notepad.viewmodels

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sidrakotlin.notepad.AppDatabase
import com.sidrakotlin.notepad.activities.NotesActivity
import com.sidrakotlin.notepad.datamodels.Note

class AppViewModel(app:Application):AndroidViewModel(app) {

    lateinit var notesLiveData:MutableLiveData<List<Note>>
    var title = MutableLiveData<String>("")
    var desc = MutableLiveData<String>("")


    init {

        notesLiveData = MutableLiveData()
    }
    fun notesObserver(): MutableLiveData<List<Note>>
    {
        return notesLiveData
    }

    fun getAllNotes()
    {
        val noteDao = AppDatabase.getInstance(getApplication()).noteDao()

        val list = noteDao.getAll()

        //update list
        notesLiveData.postValue(list!!)
    }

    fun insertNote(note: Note)
    {
        val noteDao = AppDatabase.getInstance(getApplication()).noteDao()
        noteDao.insertNote(note)
        getAllNotes()
    }

    fun deleteNote(note: Note)
    {
        val noteDao = AppDatabase.getInstance(getApplication()).noteDao()
        noteDao.deleteNote(note)
        getAllNotes()
    }

    fun updateNote(note: Note)
    {
        val noteDao = AppDatabase.getInstance(getApplication()).noteDao()
        noteDao.updateNote(note)
        getAllNotes()
    }

    fun addData()
    {
        if(title.value!!.isNotEmpty() && desc.value!!.isNotEmpty())
        {
            val note = Note(0, title.value, desc.value)
            insertNote(note)
            Toast.makeText(getApplication(),"Added",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(getApplication(),"Required",Toast.LENGTH_SHORT).show()

        }



    }


}