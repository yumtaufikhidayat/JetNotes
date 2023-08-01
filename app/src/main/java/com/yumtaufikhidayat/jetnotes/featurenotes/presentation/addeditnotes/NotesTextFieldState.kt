package com.yumtaufikhidayat.jetnotes.featurenotes.presentation.addeditnotes

data class NotesTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)