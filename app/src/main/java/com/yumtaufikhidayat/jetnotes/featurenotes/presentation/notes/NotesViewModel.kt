package com.yumtaufikhidayat.jetnotes.featurenotes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase.NotesWrapperUseCase
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils.NotesOrder
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesWrapperUseCase: NotesWrapperUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NotesStates())
    val state: State<NotesStates> = _state

    private var recentlyDeletedNotes: Notes? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NotesOrder.Date(OrderType.Descending))
    }

    fun onEvent(events: NotesEvents) {
        when (events) {
            is NotesEvents.Order -> {
                if (state.value.notesOrder::class == events.notesOrder::class &&
                    state.value.notesOrder.orderType == events.notesOrder.orderType
                ) return

                getNotes(events.notesOrder)
            }
            is NotesEvents.DeleteNotes -> {
                viewModelScope.launch {
                    notesWrapperUseCase.deleteNotesUseCase(events.notes)
                    recentlyDeletedNotes = events.notes
                }
            }
            is NotesEvents.RestoreNotes -> {
                viewModelScope.launch {
                    notesWrapperUseCase.addNotesUseCase(recentlyDeletedNotes ?: return@launch)
                    recentlyDeletedNotes = null
                }
            }
            is NotesEvents.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(notesOrder: NotesOrder) {
        getNotesJob?.cancel()
        getNotesJob = notesWrapperUseCase.getNotesUseCase(notesOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
                notesOrder = notesOrder
            )
        }.launchIn(viewModelScope)
    }
}