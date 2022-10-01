package android.project.auction.data.remote.dto.message

data class GetAllMessageByItemId(
    val `data`: List<MessagesDto>,
    val nextPage: Any,
    val pageNumber: Int,
    val pageSize: Int,
    val previousPage: Any,
    val totalDataCount: Int,
    val totalPages: Int
)