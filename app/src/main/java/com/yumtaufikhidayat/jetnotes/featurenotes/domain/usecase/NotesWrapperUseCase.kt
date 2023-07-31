package com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase

data class NotesWrapperUseCase(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNotesUseCase: DeleteNotesUseCase,
    val addNotesUseCase: AddNotesUseCase
)