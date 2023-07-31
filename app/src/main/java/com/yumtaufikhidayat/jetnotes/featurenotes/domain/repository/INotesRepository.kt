package com.yumtaufikhidayat.jetnotes.featurenotes.domain.repository

import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import kotlinx.coroutines.flow.Flow

interface INotesRepository {
    fun getAllNotes(): Flow<List<Notes>>

    suspend fun getNotesById(id: Int): Notes?

    suspend fun insertNotes(notes: Notes)

    suspend fun deleteNotes(notes: Notes)
}