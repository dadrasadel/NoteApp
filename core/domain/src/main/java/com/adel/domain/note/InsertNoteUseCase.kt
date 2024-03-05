package com.adel.domain.note

import com.adel.data.model.note.NoteEntity
import com.adel.data.repository.Repository
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun execute(noteEntity: NoteEntity){
        repository.saveNoteInDb(noteEntity)
    }
}