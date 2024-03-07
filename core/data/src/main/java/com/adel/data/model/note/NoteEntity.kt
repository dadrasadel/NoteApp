package com.adel.data.model.note

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import javax.annotation.Nonnull


@Entity
@Parcelize
data class NoteEntity (
    @Nonnull
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String,
    val content:String,
    val image:String?=null,
    val type:String,
    val timeNotified:Long?=0
):Parcelable