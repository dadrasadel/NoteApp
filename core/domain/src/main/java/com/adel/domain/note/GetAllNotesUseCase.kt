package com.adel.domain.note

import com.adel.data.model.note.NoteEntity
import com.adel.data.model.note.NoteType
import com.adel.data.repository.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import javax.inject.Inject


/**
 * get NoteEntity data based on type that pass to this class
 * @see NoteType for find all type of Notes
 */

class GetAllNotesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(time: Long) = flow<List<NoteEntity>> {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = time
        }
        val endCalendar = Calendar.getInstance().apply {
            timeInMillis = time
        }
        endCalendar.set(Calendar.HOUR, 23)
        endCalendar.set(Calendar.MINUTE, 59)
        endCalendar.set(Calendar.SECOND, 59)
        val endDate = endCalendar.timeInMillis
        calendar.set(Calendar.HOUR, 1)
        calendar.set(Calendar.MINUTE, 1)
        calendar.set(Calendar.SECOND, 1)
        val startDate = calendar.timeInMillis

        emit(repository.getNotes().filter { note -> note.timeNotified in startDate..endDate })
    }
}