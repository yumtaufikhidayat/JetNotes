package com.yumtaufikhidayat.jetnotes.featurenotes.data.repository

import com.yumtaufikhidayat.jetnotes.featurenotes.data.datasource.NotesDao
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.repository.INotesRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val notesDao: NotesDao
): INotesRepository {
    override fun getAllNotes(): Flow<List<Notes>> = notesDao.getAllNotes()

    override suspend fun getNotesById(id: Int): Notes? = notesDao.getNotesById(id)

    override suspend fun insertNotes(notes: Notes) = notesDao.insertNotes(notes)

    override suspend fun deleteNotes(notes: Notes) = notesDao.deleteNotes(notes)
}