package com.sidrakotlin.notepad.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sidrakotlin.notepad.R
import com.sidrakotlin.notepad.databinding.LayoutAddnoteBinding
import com.sidrakotlin.notepad.viewmodels.AppViewModel

class AddNoteActivity: AppCompatActivity() {
    private lateinit var binding:LayoutAddnoteBinding
    private lateinit var viewModel:AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.layout_addnote)

        //view model
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)


        binding.viewModel=viewModel
        binding.lifecycleOwner=this

        viewModel.notesObserver().observe(this, Observer {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        })

    }
}