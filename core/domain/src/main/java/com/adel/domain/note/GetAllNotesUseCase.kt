package com.adel.domain.note

import com.adel.data.model.note.NoteEntity
import com.adel.data.model.note.NoteType
import com.adel.data.repository.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * get NoteEntity data based on type that pass to this class
 * @see NoteType for find all type of Notes
 */

class GetAllNotesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator  fun invoke()= flow<List<NoteEntity>> {
            emit(repository.getNotes())
    }
}