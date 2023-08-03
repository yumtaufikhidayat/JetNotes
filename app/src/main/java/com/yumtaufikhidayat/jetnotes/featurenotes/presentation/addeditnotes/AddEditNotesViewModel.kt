package com.yumtaufikhidayat.jetnotes.featurenotes.presentation.addeditnotes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.InvalidNotesException
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.model.Notes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase.NotesWrapperUseCase
import com.yumtaufikhidayat.jetnotes.featurenotes.presentation.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNotesViewModel @Inject constructor(
    private val notesWrapperUseCase: NotesWrapperUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(NotesTextFieldState(
        hint = "Enter Title..."
    ))
    val noteTitle: State<NotesTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NotesTextFieldState(
        hint = "Enter Some Content..."
    ))
    val noteContent: State<NotesTextFieldState> = _noteContent

    private val _noteColor = mutableIntStateOf(Notes.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>(Utils.KEY_NOTES_ID)?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    notesWrapperUseCase.getNotes(noteId)?.also { notes ->
                        currentNoteId = notes.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = notes.title,
                            isHintVisible = false
                        )

                        _noteContent.value = noteContent.value.copy(
                            text = notes.content,
                            isHintVisible = false
                        )

                        _noteColor.intValue = notes.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNotesEvent) {
        when (event) {
            is AddEditNotesEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNotesEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNotesEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNotesEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }
            is AddEditNotesEvent.ChangeColor -> {
                _noteColor.intValue = event.color
            }
            is AddEditNotesEvent.SaveNotes -> {
                viewModelScope.launch {
                    try {
                        notesWrapperUseCase.addNotesUseCase(
                            Notes(
                                id = currentNoteId,
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNotes)
                    } catch (e: InvalidNotesException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(
                            message = e.message ?: "Notes couldn't saved."
                        ))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNotes: UiEvent()
    }
}