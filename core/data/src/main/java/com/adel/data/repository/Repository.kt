package com.adel.data.repository

import com.adel.data.model.note.NoteEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveNoteInDb(note: NoteEntity)
    suspend fun getNotes(): List<NoteEntity>
}