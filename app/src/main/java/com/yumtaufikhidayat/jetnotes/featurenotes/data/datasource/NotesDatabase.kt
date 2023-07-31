package com.yumtaufikhidayat.jetnotes.featurenotes.data.datasource

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract val noteDao: NotesDao
}