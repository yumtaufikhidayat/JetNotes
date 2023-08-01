package com.yumtaufikhidayat.jetnotes.featurenotes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yumtaufikhidayat.jetnotes.ui.theme.BabyBlue
import com.yumtaufikhidayat.jetnotes.ui.theme.LightGreen
import com.yumtaufikhidayat.jetnotes.ui.theme.RedOrange
import com.yumtaufikhidayat.jetnotes.ui.theme.RedPink
import com.yumtaufikhidayat.jetnotes.ui.theme.Violet

@Entity
data class Notes(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
) {
    companion object {
        val noteColors = listOf(
            RedOrange,
            LightGreen,
            Violet,
            BabyBlue,
            RedPink
        )
    }
}
