package com.adel.data.model

import com.adel.data.model.note.NoteEntity
import com.adel.data.model.note.NoteType
import org.junit.Assert
import org.junit.Test


class NoteEntityTest{


    @Test
    fun `User Entity should be not null`() {
        //Given
        val noteEntity=
            NoteEntity(
                id = 0,
                title = "this is sample title",
                content = "this is sample test",
                type = NoteType.Work.noteName,
                image = "sample image path",
            )
        //then
        Assert.assertNotNull(noteEntity.id)
        Assert.assertNotNull(noteEntity.content)
        Assert.assertNotNull(noteEntity.title)
        Assert.assertEquals(noteEntity.type,NoteType.Work.noteName)
        Assert.assertNotEquals(noteEntity.type,NoteType.LifeStyle.noteName)
        Assert.assertEquals(noteEntity.image,"sample image path")

    }
}