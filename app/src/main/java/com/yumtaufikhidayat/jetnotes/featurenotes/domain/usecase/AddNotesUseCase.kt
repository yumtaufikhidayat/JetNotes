package com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase

import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.InvalidNotesException
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.repository.INotesRepository
import kotlin.jvm.Throws

class AddNotesUseCase(
    private val repository: INotesRepository
) {

    @Throws(InvalidNotesException::class)
    suspend operator fun invoke(notes: Notes) {
        notes.title.ifBlank {
            throw InvalidNotesException("The title can't be empty.")
        }
        notes.content.ifBlank {
            throw InvalidNotesException("The content can't be empty")
        }
        repository.insertNotes(notes)
    }
}