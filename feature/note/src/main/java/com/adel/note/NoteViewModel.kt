package com.adel.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adel.data.model.note.NoteEntity
import com.adel.data.model.note.NoteType
import com.adel.data.model.note.TabHeaderItem
import com.adel.domain.note.GetAllNotesUseCase
import com.adel.domain.note.InsertNoteUseCase
import com.adel.note.state.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase

) : ViewModel() {
    private val _noteList = MutableStateFlow<NotesState>(NotesState.Loading)
    val noteList: StateFlow<NotesState> = _noteList


    init {
        getNoteList(System.currentTimeMillis())
    }

    /**
     * get notes based on type
     */
    fun getNoteList(timeInMillis:Long) {
        viewModelScope.launch(Dispatchers.IO) {
            coroutineScope {
                getAllNotesUseCase.invoke(timeInMillis).collect { noteList ->
                    _noteList.update {
                        NotesState.Success(noteList,listOf(
                            TabHeaderItem(
                                title = NoteType.All.noteName,
                                count = noteList.size
                            ),
                            TabHeaderItem(
                                title = NoteType.Work.noteName,
                                count = noteList.filter { it.type==NoteType.Work.noteName }.size
                            ),
                            TabHeaderItem(
                                title = NoteType.LifeStyle.noteName,
                                count = noteList.filter { it.type==NoteType.LifeStyle.noteName }.size
                            )
                        ))
                    }
                }


            }
        }
    }

    fun insetNoteEntity(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase.execute(noteEntity)
        }
    }
}