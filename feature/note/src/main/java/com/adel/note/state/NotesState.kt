package com.adel.note.state

import com.adel.data.model.note.NoteEntity
import com.adel.data.model.note.TabHeaderItem

sealed interface NotesState {
    data object Loading:NotesState
    data class Success(val noteList: List<NoteEntity>,
                       val tabList: List<TabHeaderItem> ) : NotesState
}
