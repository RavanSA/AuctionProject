package android.project.auction.domain.model.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}