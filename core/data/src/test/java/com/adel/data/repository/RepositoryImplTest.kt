package com.adel.data.repository

import com.adel.data.database.impl.DataBaseRequest
import com.adel.data.model.note.NoteEntity
import com.adel.data.model.note.NoteType
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RepositoryImplTest{

    val mockDataBaseHelper: DataBaseRequest = mock()
        private lateinit var appRepository:Repository
    @Before
    fun init(){
        appRepository = RepositoryImpl(
            dataBaseRequest = mockDataBaseHelper,
        )
    }

    @Test
    fun `check database call from repository working right or not`()= runTest{
//        appRepository = RepositoryImpl(
//            dataBaseRequest = mockDataBaseHelper,
//        )
//        /* Given */
        val noteEntity=
            NoteEntity(
                id = 0,
                title = "this is sample title",
                content = "this is sample test",
                type = NoteType.Work.noteName,
                image = "sample image path",
            )
        Mockito.`when`(appRepository.getNotes()).thenReturn(
                listOf(noteEntity)

        )
//
//        /* Apply */
       val notes= appRepository.getNotes()


        /* Assert */
        Assertions.assertEquals(notes[0].id, noteEntity.id)
        verify(mockDataBaseHelper).getNotes()
    }
}