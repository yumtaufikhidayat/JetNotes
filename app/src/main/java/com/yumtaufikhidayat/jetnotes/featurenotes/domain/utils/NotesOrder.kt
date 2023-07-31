package com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils

sealed class NotesOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NotesOrder(orderType)
    class Date(orderType: OrderType): NotesOrder(orderType)
    class Color(orderType: OrderType): NotesOrder(orderType)
}