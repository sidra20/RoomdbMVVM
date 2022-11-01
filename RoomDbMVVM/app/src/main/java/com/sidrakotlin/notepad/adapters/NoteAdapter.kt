package com.sidrakotlin.notepad.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sidrakotlin.notepad.R
import com.sidrakotlin.notepad.datamodels.Note

class NoteAdapter(val listener:ChangesToNote ,private val noteList:List<Note>, private val context:Context): RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view:View = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false)
        val obj = MyViewHolder(view, listener)
        return obj
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val notes = noteList[position]
        holder.title.text = notes.title
        holder.desc.text = notes.desc

        holder.delete.setOnClickListener{
            listener.deleteNote(notes)
        }
        holder.itemView.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
       return noteList.size
    }

    class MyViewHolder(itemView:View, val listener: ChangesToNote):RecyclerView.ViewHolder(itemView)
    {

        var title:TextView = itemView.findViewById(R.id.noteTitle)
        var desc:TextView = itemView.findViewById(R.id.noteDesc)
        var delete:ImageView = itemView.findViewById(R.id.deleteNote)

        fun bind(note:Note)
        {
            title.text = note.title
            desc.text = note.desc
        }

    }

    interface ChangesToNote{
        fun deleteNote(note: Note)
        fun updateNote(note: Note)

    }

}

