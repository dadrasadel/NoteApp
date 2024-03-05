package com.adel.data.database.impl

import com.adel.data.model.note.NoteEntity
import kotlinx.coroutines.flow.Flow
 interface DataBaseRequest {
    suspend fun getNotes(): List<NoteEntity>
    suspend fun insertNotes(notes: NoteEntity)
}