package com.adel.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adel.data.database.dao.NoteDao
import com.adel.data.model.note.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
    ],
    version = Constant.VERSION
)
@TypeConverters
internal abstract class AppDataBase : RoomDatabase() {
    abstract val noteDao: NoteDao
}