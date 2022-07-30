package android.project.auction.data.remote.dto.items.getitems

data class ItemDto(
    val `data`: List<ItemDataDto>,
    val nextPage: Any,
    val pageNumber: Int,
    val pageSize: Int,
    val previousPage: Any,
    val totalDataCount: Int,
    val totalPages: Int
)