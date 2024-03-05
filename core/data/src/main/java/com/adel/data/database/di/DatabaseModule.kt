package com.adel.data.database.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.adel.data.database.AppDataBase
import com.adel.data.database.Constant
import com.adel.data.database.JsonConverter
import com.adel.data.database.MIGRATIONS
import com.adel.data.database.dao.NoteDao
import com.adel.data.database.impl.DataBaseRequest
import com.adel.data.database.impl.DataBaseRequestImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    @Suppress("SpreadOperator")
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            Constant.DATABASE_NAME
        ).addMigrations(*MIGRATIONS)
            .build()
    }

    @Provides
    @Singleton
    fun provideNote(appDataBase: AppDataBase): NoteDao {
        return appDataBase.noteDao
    }


    @Provides
    @Singleton
    fun provideJsonConverter(gson: Gson): JsonConverter = JsonConverter(gson)


    @Provides
    @Singleton
    fun provideDbImplementation(dataBaseRequestImpl: DataBaseRequestImpl): DataBaseRequest =dataBaseRequestImpl
}