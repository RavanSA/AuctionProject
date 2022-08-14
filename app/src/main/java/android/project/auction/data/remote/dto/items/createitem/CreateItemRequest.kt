package android.project.auction.data.remote.dto.items.createitem

data class CreateItemRequest(
    val title: String,
    val description: String,
    val startingPrice: Double,
    val minIncrease: Double,
    val startTime: String,
    val endTime: String,
    val subCategoryId: String,
    val categoryId: String,
    val userId: String
)
