package com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase

import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.repository.INotesRepository

class DeleteNotesUseCase(
    private val repository: INotesRepository
) {
    suspend operator fun invoke(notes: Notes) {
        repository.deleteNotes(notes)
    }
}