package com.adel.data.database.impl

import com.adel.data.database.dao.NoteDao
import com.adel.data.model.note.NoteEntity
import javax.inject.Inject

internal class DataBaseRequestImpl @Inject constructor(private val noteDao: NoteDao):
    DataBaseRequest {
    /**
    * get all notes from local db
    */
    override suspend fun getNotes()= noteDao.getNotes()
    /**
     * insert notes to database
     */
    override suspend fun insertNotes(note:NoteEntity)= noteDao.insert(notes = note)
}