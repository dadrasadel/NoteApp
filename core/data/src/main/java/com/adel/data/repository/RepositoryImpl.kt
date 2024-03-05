package com.adel.data.repository
import com.adel.data.database.impl.DataBaseRequest
import com.adel.data.model.note.NoteEntity
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
   private val  dataBaseRequest: DataBaseRequest
):Repository {
    /**
     * insert NoteEntity to DB
     * @see dataBaseRequest.insertNotes()
     */
    override suspend fun saveNoteInDb(note:NoteEntity){
        dataBaseRequest.insertNotes(notes = note)
    }
    /**
     * get data from data base
     * return list of NoteEntity
     * @see dataBaseRequest.getNotes()
     */

    override suspend fun getNotes() = dataBaseRequest.getNotes()

}