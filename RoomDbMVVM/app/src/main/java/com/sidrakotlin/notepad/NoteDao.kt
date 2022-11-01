package com.sidrakotlin.notepad
import androidx.lifecycle.LiveData
import androidx.room.*
import com.sidrakotlin.notepad.datamodels.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): List<Note>?

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)
}