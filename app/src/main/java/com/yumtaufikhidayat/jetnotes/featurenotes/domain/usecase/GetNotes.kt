package com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase

import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.repository.INotesRepository

class GetNotes(
    private val repository: INotesRepository
) {
    suspend operator fun invoke(id: Int): Notes? {
        return repository.getNotesById(id)
    }
}