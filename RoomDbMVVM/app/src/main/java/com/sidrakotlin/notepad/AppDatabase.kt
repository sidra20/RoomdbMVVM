package com.sidrakotlin.notepad
import android.content.Context
import androidx.room.*
import com.sidrakotlin.notepad.datamodels.Note

@Database(entities = [(Note::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object{
        private var instance:AppDatabase?=null

        fun getInstance(context: Context):AppDatabase
        {
            if(instance==null)
            {

                synchronized(this)
                {


                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "notedb"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }

    }
}