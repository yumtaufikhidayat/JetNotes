package com.yumtaufikhidayat.jetnotes.featurenotes.domain.utils

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
