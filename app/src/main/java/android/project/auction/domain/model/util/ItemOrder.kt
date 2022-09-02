package android.project.auction.domain.model.util

sealed class ItemOrder(val orderType: OrderType) {
    class StartingPrice(orderType: OrderType) : ItemOrder(orderType)
    class Created(orderType: OrderType) : ItemOrder(orderType)

    fun copy(orderType: OrderType): ItemOrder {
        return when (this) {
            is StartingPrice -> StartingPrice(orderType)
            is Created -> Created(orderType)
        }
    }
}