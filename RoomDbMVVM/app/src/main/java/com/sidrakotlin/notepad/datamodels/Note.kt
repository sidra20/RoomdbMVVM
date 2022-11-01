package com.sidrakotlin.notepad.datamodels

import androidx.room.Entity
import androidx.room.*

@Entity(tableName = "note")
data class Note(@PrimaryKey(autoGenerate = true)
                val id:Int=0,
                @ColumnInfo(name="title") val title:String?,
                @ColumnInfo(name="desc") val desc:String?,

                )

