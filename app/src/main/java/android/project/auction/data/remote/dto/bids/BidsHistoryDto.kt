package android.project.auction.data.remote.dto.bids

data class BidsHistoryDto(
    val `data`: List<BidHistoryDataDto>,
    val nextPage: Any,
    val pageNumber: Int,
    val pageSize: Int,
    val previousPage: Any,
    val totalDataCount: Int,
    val totalPages: Int
)