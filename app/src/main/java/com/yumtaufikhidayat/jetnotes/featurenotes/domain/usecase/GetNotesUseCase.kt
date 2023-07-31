package com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase

import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.repository.INotesRepository
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils.NotesOrder
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: INotesRepository
) {
    operator fun invoke(
        notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending)
    ): Flow<List<Notes>> {
        return repository.getAllNotes().map { notes ->
            when (notesOrder.orderType) {
                is OrderType.Ascending -> {
                    when (notesOrder) {
                        is NotesOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NotesOrder.Date -> notes.sortedBy { it.timestamp }
                        is NotesOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (notesOrder) {
                        is NotesOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NotesOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NotesOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}