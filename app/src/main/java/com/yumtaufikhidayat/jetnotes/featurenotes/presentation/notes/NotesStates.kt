package com.yumtaufikhidayat.jetnotes.featurenotes.presentation.notes

import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils.NotesOrder
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils.OrderType

data class NotesStates(
    val notes: List<Notes> = emptyList(),
    val notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
