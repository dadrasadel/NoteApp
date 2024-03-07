package com.adel.data.database.impl

import com.adel.data.database.dao.NoteDao
import com.adel.data.model.note.NoteEntity
import com.adel.data.model.note.NoteType
import com.adel.data.repository.Repository
import com.adel.data.repository.RepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DataBaseRequestImplTest{
    val mockDao: NoteDao = mock()
    private lateinit var dataBaseRequestImpl: DataBaseRequest
    @Before
    fun init(){
        dataBaseRequestImpl = DataBaseRequestImpl(
            noteDao = mockDao,
        )
    }

    @Test
    fun `check database work correctly for getting data`()= runTest{
//
//        /* Given */
        val noteEntity=
            NoteEntity(
                id = 0,
                title = "this is sample title",
                content = "this is sample test",
                type = NoteType.Work.noteName,
                image = "sample image path",
            )
        Mockito.`when`(dataBaseRequestImpl.getNotes()).thenReturn(
            listOf(noteEntity)

        )
//
//        /* Apply */
        val notes= dataBaseRequestImpl.getNotes()


        /* Assert */
        Assertions.assertEquals(notes[0].id, noteEntity.id)
        Assertions.assertNotNull(notes[0].id)
    }
}