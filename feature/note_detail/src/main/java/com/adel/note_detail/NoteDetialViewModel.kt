package com.adel.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adel.data.model.note.NoteEntity
import com.adel.domain.note.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class NoteDetialViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
) : ViewModel() {
    fun insetNoteEntity(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            coroutineScope {
            insertNoteUseCase.execute(noteEntity)
            }
        }
    }
}