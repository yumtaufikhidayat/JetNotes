package com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase

data class NotesUseCase(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNotesUseCase: DeleteNotesUseCase
)