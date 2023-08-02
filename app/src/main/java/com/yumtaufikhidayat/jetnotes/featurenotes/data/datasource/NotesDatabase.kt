package com.yumtaufikhidayat.jetnotes.featurenotes.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract val noteDao: NotesDao

    companion object {
        const val DB_NAME = "notes_db"
    }
}