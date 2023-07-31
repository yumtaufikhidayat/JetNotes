package com.yumtaufikhidayat.jetnotes.featurenotes.presentation.notes

import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils.NotesOrder

sealed class NotesEvents {
    data class Order(val notesOrder: NotesOrder) : NotesEvents()
    data class DeleteNotes(val notes: Notes) : NotesEvents()
    object RestoreNotes: NotesEvents()
    object ToggleOrderSection: NotesEvents()
}
