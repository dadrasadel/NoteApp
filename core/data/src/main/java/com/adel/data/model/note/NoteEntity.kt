package com.adel.data.model.note

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val content:String,
    val type:Int,
    val timeNotified:Long
)